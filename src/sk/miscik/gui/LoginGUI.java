package sk.miscik.gui;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sk.miscik.main.HTMLRequest;
import sk.miscik.main.Main;
import sk.miscik.main.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * Created by client on 09.02.2018.
 */
public class LoginGUI extends JFrame {

    public static final int WIDTH = 400, HEIGHT = 150;

    private JTextField userField;
    private JPasswordField passField;

    public LoginGUI() throws Exception {
        super("ProjektHR Login");
        Font font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("segoeuil.ttf"));

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().setBackground(new Color(57,57,71));
        this.getContentPane().setLayout(null);
        this.setResizable(false);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setSize(80,30);
        userLabel.setLocation(10,10);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(font.deriveFont(16f));
        this.getContentPane().add(userLabel);

        this.userField = new JTextField();
        this.userField.setSize(300, 30);
        this.userField.setLocation(90, 10);
        this.userField.setFont(font.deriveFont(16f));
        this.getContentPane().add(this.userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setSize(80,30);
        passLabel.setLocation(10,50);
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(font.deriveFont(16f));
        this.getContentPane().add(passLabel);

        this.passField = new JPasswordField();
        this.passField.setSize(300, 30);
        this.passField.setLocation(90, 50);
        this.passField.setFont(font.deriveFont(16f));
        this.getContentPane().add(this.passField);

        JButton loginButton = new JButton("Log in");
        loginButton.setSize(150,30);
        loginButton.setLocation(WIDTH/2-75, 100);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setForeground(Color.WHITE);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setForeground(Color.WHITE);
            }
        });
        loginButton.setFont(font.deriveFont(16f));
        loginButton.addActionListener(e ->{
            if (userField.getText().length() == 0 || passField.getPassword().length == 0) {
                JOptionPane.showMessageDialog(null, "Please enter your credentials!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String userName = this.userField.getText();
            char[] pass = this.passField.getPassword();
            String message = "{\"userName\": \""+userName+"\", \"password\": \""+String.valueOf(pass)+"\"}";
            try {
                String response = HTMLRequest.getInstance().getAuth("http://194.160.229.181:8081/api/auth/login", message);
                if (response.startsWith("ERROR_")) {
                    int errCode = Integer.parseInt(response.split("_")[1]);
                    if (errCode == 401) {
                        JOptionPane.showMessageDialog(null, "Unauthorized.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Code: "+errCode, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    System.out.println(response);
                    JSONParser parser = new JSONParser();
                    JSONObject tree = (JSONObject) parser.parse(response);
                    JSONObject userTree = (JSONObject) tree.get("user");
                    String firstName = (String) userTree.get("firstName");
                    String lastName = (String) userTree.get("lastName");
                    String email = (String) userTree.get("email");
                    String avatarURL = ((String) userTree.get("photoUrl")).replace("localhost","194.160.229.181");
                    String token = (String) tree.get("token");
                    User user = new User(firstName,lastName, token, email,avatarURL);
                    this.dispose();
                    new ManagementGUI(user).setVisible(true);
                }
            } catch (Exception ex) {ex.printStackTrace();}
        });
        this.getContentPane().add(loginButton);

        this.pack();
        this.setLocationRelativeTo(null);
    }

}
