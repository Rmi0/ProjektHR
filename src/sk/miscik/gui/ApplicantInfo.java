package sk.miscik.gui;

import sk.miscik.main.Applicant;
import sk.miscik.main.HTTPRequest;
import sk.miscik.main.Main;
import sk.miscik.main.User;

import javax.swing.*;
import java.awt.*;

public class ApplicantInfo extends JDialog {

    public ApplicantInfo(User user, Applicant applicant, JFrame frame) {
        super(frame, true);
        if (applicant == null) {this.dispose(); return;}
        this.setTitle(applicant.format("!f !l"));
        this.setResizable(false);
        this.getContentPane().setMinimumSize(new Dimension(400,350));
        this.getContentPane().setPreferredSize(new Dimension(400,350));
        this.getContentPane().setMaximumSize(new Dimension(400,350));
        this.getContentPane().setBackground(new Color(57,57,71));
        this.getContentPane().setLayout(null);

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("segoeuil.ttf"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JLabel nameLabel = new JLabel(applicant.format("Name:    !f !l"));
        nameLabel.setFont(font.deriveFont(18f));
        nameLabel.setSize(380, 30);
        nameLabel.setLocation(10,10);
        nameLabel.setForeground(Color.WHITE);
        this.getContentPane().add(nameLabel);

        JLabel emailLabel = new JLabel(applicant.format("E-mail:    !e"));
        emailLabel.setFont(font.deriveFont(18f));
        emailLabel.setSize(380, 30);
        emailLabel.setLocation(10,50);
        emailLabel.setForeground(Color.WHITE);
        this.getContentPane().add(emailLabel);

        JLabel phoneLabel = new JLabel(applicant.format("Phone:    !n"));
        phoneLabel.setFont(font.deriveFont(18f));
        phoneLabel.setSize(380, 30);
        phoneLabel.setLocation(10,90);
        phoneLabel.setForeground(Color.WHITE);
        this.getContentPane().add(phoneLabel);

        JLabel cityLabel = new JLabel(applicant.format("City:    !c"));
        cityLabel.setFont(font.deriveFont(18f));
        cityLabel.setSize(380, 30);
        cityLabel.setLocation(10,130);
        cityLabel.setForeground(Color.WHITE);
        this.getContentPane().add(cityLabel);

        JLabel roomLabel = new JLabel(applicant.format("Room:    !r"));
        roomLabel.setFont(font.deriveFont(18f));
        roomLabel.setSize(380, 30);
        roomLabel.setLocation(10,170);
        roomLabel.setForeground(Color.WHITE);
        this.getContentPane().add(roomLabel);

        JLabel positionLabel = new JLabel(applicant.format("Positon:    !p"));
        positionLabel.setFont(font.deriveFont(18f));
        positionLabel.setSize(380, 30);
        positionLabel.setLocation(10,210);
        positionLabel.setForeground(Color.WHITE);
        this.getContentPane().add(positionLabel);

        String[] date = applicant.getDate().split("T");
        JLabel dateLabel = new JLabel(applicant.format("Date:    "+date[0]+" "+date[1]));
        dateLabel.setFont(font.deriveFont(18f));
        dateLabel.setSize(380, 30);
        dateLabel.setLocation(10,250);
        dateLabel.setForeground(Color.WHITE);
        this.getContentPane().add(dateLabel);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(font.deriveFont(18f));
        deleteButton.setSize(380, 40);
        deleteButton.setLocation(10,300);
        deleteButton.addActionListener(e -> {
            HTTPRequest.getInstance().deleteCandidate(user, applicant);
            this.dispose();
        });
        this.getContentPane().add(deleteButton);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
