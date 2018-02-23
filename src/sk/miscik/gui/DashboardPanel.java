package sk.miscik.gui;

import sk.miscik.main.Main;
import sk.miscik.main.User;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends ContentPanel {

    public DashboardPanel(User user) throws Exception {
        super(user);
        this.setLayout(null);
        Font font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("segoeuil.ttf"));

        JPanel userPanel = new JPanel();
        userPanel.setSize(650,72);
        userPanel.setLocation(10,10);
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

        this.add(userPanel);
    }

}
