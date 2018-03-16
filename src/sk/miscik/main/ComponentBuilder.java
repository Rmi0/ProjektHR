package sk.miscik.main;

import sk.miscik.gui.CustomCBUI;
import sk.miscik.gui.LoginGUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by client on 23.02.2018.
 */
public class ComponentBuilder {

    public static ArrayList<Component> getDashboardComponents(User user) {
        ArrayList<Component> Cs = new ArrayList<>();

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("segoeuil.ttf"));
        } catch (Exception ex) {ex.printStackTrace();}

        JPanel userPanel = new JPanel();
        userPanel.setSize(650,72);
        userPanel.setLocation(140,50);
        userPanel.setBackground(new Color(48,48,67));
        userPanel.setLayout(null);

        JLabel avatarLabel = new JLabel(new ImageIcon(user.getAvatar()));
        avatarLabel.setLocation(4,4);
        avatarLabel.setSize(64,64);
        userPanel.add(avatarLabel);

        JLabel nameLabel = new JLabel(user.getFirstName()+" "+user.getLastName());
        nameLabel.setSize(571,30);
        nameLabel.setLocation(75,4);
        nameLabel.setBackground(new Color(48,48,67));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(font.deriveFont(18f));
        userPanel.add(nameLabel);

        JLabel emailLabel = new JLabel(user.getEmail());
        emailLabel.setSize(571,30);
        emailLabel.setLocation(75,38);
        emailLabel.setBackground(new Color(48,48,67));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(font.deriveFont(18f));
        userPanel.add(emailLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setSize(100,40);
        logoutButton.setLocation(userPanel.getWidth()-100, userPanel.getHeight()-40);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setMargin(new Insets(0,0,0,0));
        logoutButton.setBorder(null);
        logoutButton.setFont(font.deriveFont(28f));
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logoutButton.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logoutButton.setForeground(Color.WHITE);
            }
        });
        logoutButton.addActionListener(e -> {
            ((JFrame)(userPanel.getParent().getParent().getParent().getParent())).dispose();
            try {
                HTTPRequest.getInstance().logout(user);
                new LoginGUI().setVisible(true);
            } catch (Exception ex) {ex.printStackTrace();}
        });
        userPanel.add(logoutButton);

        Cs.add(userPanel);
        return Cs;
    }

    public static ArrayList<Component> getNewComponents(User user) throws Exception {
        ArrayList<Component> Cs = new ArrayList<>();

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("segoeuil.ttf"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //-------- FIRST ROW
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setSize(250, 40);
        firstNameLabel.setLocation(150,60);
        firstNameLabel.setForeground(Color.WHITE);
        firstNameLabel.setFont(font.deriveFont(18f));
        Cs.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setSize(250, 40);
        firstNameField.setLocation(150,100);
        firstNameField.setBorder(new MatteBorder(0,0,1,0, Color.WHITE));
        firstNameField.setForeground(Color.WHITE);
        firstNameField.setOpaque(false);
        firstNameField.setCaretColor(Color.WHITE);
        firstNameField.setFont(font.deriveFont(24f));
        Cs.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setSize(250, 40);
        lastNameLabel.setLocation(530,60);
        lastNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setFont(font.deriveFont(18f));
        Cs.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setSize(250, 40);
        lastNameField.setLocation(530,100);
        lastNameField.setBorder(new MatteBorder(0,0,1,0, Color.WHITE));
        lastNameField.setForeground(Color.WHITE);
        lastNameField.setOpaque(false);
        lastNameField.setCaretColor(Color.WHITE);
        lastNameField.setFont(font.deriveFont(24f));
        Cs.add(lastNameField);

        //-------- SECOND ROW

        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setSize(250, 40);
        emailLabel.setLocation(150,160);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(font.deriveFont(18f));
        Cs.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setSize(250, 40);
        emailField.setLocation(150,200);
        emailField.setBorder(new MatteBorder(0,0,1,0, Color.WHITE));
        emailField.setForeground(Color.WHITE);
        emailField.setOpaque(false);
        emailField.setCaretColor(Color.WHITE);
        emailField.setFont(font.deriveFont(24f));
        Cs.add(emailField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setSize(250, 40);
        phoneLabel.setLocation(530,160);
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setFont(font.deriveFont(18f));
        Cs.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setSize(250, 40);
        phoneField.setLocation(530,200);
        phoneField.setBorder(new MatteBorder(0,0,1,0, Color.WHITE));
        phoneField.setForeground(Color.WHITE);
        phoneField.setOpaque(false);
        phoneField.setCaretColor(Color.WHITE);
        phoneField.setFont(font.deriveFont(24f));
        Cs.add(phoneField);

        //-------- THIRD ROW

        UIManager.setLookAndFeel(Main.defaultLAF);
        JComboBox<String> cityBox = new JComboBox<>();
        cityBox.setSize(250, 40);
        cityBox.setLocation(150,300);
        cityBox.setUI(new CustomCBUI());
        cityBox.setForeground(Color.WHITE);
        cityBox.setBackground(new Color(48,48,67));
        cityBox.setFont(font.deriveFont(24f));
        for (String s : HTTPRequest.getInstance().getCities(user)) {
            cityBox.addItem(s);
        }
        Cs.add(cityBox);

        JComboBox<String> roomBox = new JComboBox<>();
        roomBox.setSize(250, 40);
        roomBox.setLocation(530,300);
        roomBox.setUI(new CustomCBUI());
        roomBox.setForeground(Color.WHITE);
        roomBox.setBackground(new Color(48,48,67));
        roomBox.setFont(font.deriveFont(24f));
        for (String s : HTTPRequest.getInstance().getRooms(user, (String) cityBox.getSelectedItem())) {
            roomBox.addItem(s);
        }
        cityBox.addActionListener(e -> {
            try {
                roomBox.removeAllItems();
                for (String s : HTTPRequest.getInstance().getRooms(user, (String) cityBox.getSelectedItem())) {
                    roomBox.addItem(s);
                }
            } catch (Exception ex) {ex.printStackTrace();}
        });
        Cs.add(roomBox);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        //-------- FOURTH ROW

        UIManager.setLookAndFeel(Main.defaultLAF);
        JComboBox<String> positionBox = new JComboBox<>();
        positionBox.setSize(250, 40);
        positionBox.setLocation(150,400);
        positionBox.setUI(new CustomCBUI());
        positionBox.setForeground(Color.WHITE);
        positionBox.setBackground(new Color(48,48,67));
        positionBox.setFont(font.deriveFont(24f));
        for (String s : HTTPRequest.getInstance().getPositions(user)) {
            positionBox.addItem(s);
        }
        Cs.add(positionBox);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        //-------- END

        JButton submitButton = new JButton("Submit");
        submitButton.setSize(200,40);
        submitButton.setLocation(355,640);
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setContentAreaFilled(false);
        submitButton.setForeground(Color.WHITE);
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submitButton.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submitButton.setForeground(Color.WHITE);
            }
        });
        submitButton.setFont(font.deriveFont(30f));
        submitButton.addActionListener(e -> {
            try {
                Applicant applicant = new Applicant(firstNameField.getText(), lastNameField.getText(), emailField.getText(),
                        phoneField.getText(), (String) cityBox.getSelectedItem(), (String) roomBox.getSelectedItem(),
                        (String) positionBox.getSelectedItem());
                HTTPRequest.getInstance().submitInterview(user, applicant);
                //JOptionPane.showMessageDialog(null, "Operation completed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                firstNameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
                phoneField.setText("");
            } catch (Exception ex) {ex.printStackTrace();}
        });
        Cs.add(submitButton);

        return Cs;
    }

    public static ArrayList<Component> getViewComponents(User user) {
        ArrayList<Component> Cs = new ArrayList<>();

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("segoeuil.ttf"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JLabel row1 = new JLabel("");
        row1.setSize(780,60);
        row1.setLocation(140,50);
        row1.setFont(font.deriveFont(36f));
        row1.setForeground(Color.WHITE);
        Cs.add(row1);

        JLabel row2 = new JLabel("");
        row2.setSize(780,60);
        row2.setLocation(140,120);
        row2.setFont(font.deriveFont(36f));
        row2.setForeground(Color.WHITE);
        Cs.add(row2);

        JLabel row3 = new JLabel("");
        row3.setSize(780,60);
        row3.setLocation(140,190);
        row3.setFont(font.deriveFont(36f));
        row3.setForeground(Color.WHITE);
        Cs.add(row3);

        JLabel row4 = new JLabel("");
        row4.setSize(780,60);
        row4.setLocation(140,260);
        row4.setFont(font.deriveFont(36f));
        row4.setForeground(Color.WHITE);
        Cs.add(row4);

        JLabel row5 = new JLabel("");
        row5.setSize(780,60);
        row5.setLocation(140,330);
        row5.setFont(font.deriveFont(36f));
        row5.setForeground(Color.WHITE);
        Cs.add(row5);

        row1.setText("Test Test");
        row2.setText("Test Test");
        row3.setText("Test Test");
        row4.setText("Test Test");
        row5.setText("Test Test");

        JButton prev = new JButton("<< Prev");
        prev.setSize(100,25);
        prev.setLocation(140, 400);
        Cs.add(prev);

        JButton next = new JButton("Next >>");
        next.setSize(100,25);
        next.setLocation(690, 400);
        Cs.add(next);

        return Cs;
    }

}
