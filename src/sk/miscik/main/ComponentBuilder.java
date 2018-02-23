package sk.miscik.main;

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
                HTMLRequest.getInstance().logout(user);
                new LoginGUI().setVisible(true);
            } catch (Exception ex) {ex.printStackTrace();}
        });
        userPanel.add(logoutButton);

        Cs.add(userPanel);
        return Cs;
    }

    public static ArrayList<Component> getNewComponents(User user) {
        ArrayList<Component> Cs = new ArrayList<>();

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("segoeuil.ttf"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setSize(300, 40);
        firstNameLabel.setLocation(150,60);
        firstNameLabel.setForeground(Color.WHITE);
        firstNameLabel.setFont(font.deriveFont(18f));
        Cs.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setSize(300, 40);
        firstNameField.setLocation(150,100);
        firstNameField.setBorder(new MatteBorder(0,0,1,0, Color.WHITE));
        firstNameField.setForeground(Color.WHITE);
        firstNameField.setOpaque(false);
        firstNameField.setCaretColor(Color.WHITE);
        firstNameField.setFont(font.deriveFont(24f));
        Cs.add(firstNameField);

        return Cs;
    }
}
