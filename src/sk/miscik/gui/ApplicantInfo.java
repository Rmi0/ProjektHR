package sk.miscik.gui;

import sk.miscik.main.Applicant;
import sk.miscik.main.HTTPRequest;
import sk.miscik.main.Main;
import sk.miscik.main.User;

import javax.swing.*;
import java.awt.*;

public class ApplicantInfo extends JDialog {

    public ApplicantInfo(User user, Applicant applicant, ManagementGUI frame) {
        super(frame, true);
        if (applicant == null) {this.dispose(); return;}
        this.setTitle(applicant.format("!i | !f !l"));
        this.setResizable(false);
        this.getContentPane().setMinimumSize(new Dimension(400,460));
        this.getContentPane().setPreferredSize(new Dimension(400,460));
        this.getContentPane().setMaximumSize(new Dimension(400,460));
        this.getContentPane().setBackground(new Color(57,57,71));
        this.getContentPane().setLayout(null);

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("segoeuil.ttf"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JLabel idLabel = new JLabel(applicant.format("ID:   !i"));
        idLabel.setFont(font.deriveFont(18f));
        idLabel.setSize(380, 30);
        idLabel.setLocation(10,10);
        idLabel.setForeground(Color.WHITE);
        this.getContentPane().add(idLabel);

        JLabel nameLabel = new JLabel(applicant.format("Name:   !f !l"));
        nameLabel.setFont(font.deriveFont(18f));
        nameLabel.setSize(380, 30);
        nameLabel.setLocation(10,50);
        nameLabel.setForeground(Color.WHITE);
        this.getContentPane().add(nameLabel);

        JLabel emailLabel = new JLabel(applicant.format("E-mail:   !e"));
        emailLabel.setFont(font.deriveFont(18f));
        emailLabel.setSize(380, 30);
        emailLabel.setLocation(10,90);
        emailLabel.setForeground(Color.WHITE);
        this.getContentPane().add(emailLabel);

        JLabel phoneLabel = new JLabel(applicant.format("Phone:   !n"));
        phoneLabel.setFont(font.deriveFont(18f));
        phoneLabel.setSize(380, 30);
        phoneLabel.setLocation(10,130);
        phoneLabel.setForeground(Color.WHITE);
        this.getContentPane().add(phoneLabel);

        JLabel cityLabel = new JLabel(applicant.format("City:   !c"));
        cityLabel.setFont(font.deriveFont(18f));
        cityLabel.setSize(380, 30);
        cityLabel.setLocation(10,170);
        cityLabel.setForeground(Color.WHITE);
        this.getContentPane().add(cityLabel);

        JLabel roomLabel = new JLabel(applicant.format("Room:   !r"));
        roomLabel.setFont(font.deriveFont(18f));
        roomLabel.setSize(380, 30);
        roomLabel.setLocation(10,210);
        roomLabel.setForeground(Color.WHITE);
        this.getContentPane().add(roomLabel);

        JLabel positionLabel = new JLabel(applicant.format("Positon:   !p"));
        positionLabel.setFont(font.deriveFont(18f));
        positionLabel.setSize(380, 30);
        positionLabel.setLocation(10,250);
        positionLabel.setForeground(Color.WHITE);
        this.getContentPane().add(positionLabel);

        String[] dateTime = applicant.getDate().split("T");
        String[] date = dateTime[0].split("-");
        JLabel dateLabel = new JLabel(applicant.format("Date:   "+date[2]+"/"+date[1]+"/"+date[0]+" "+dateTime[1]));
        dateLabel.setFont(font.deriveFont(18f));
        dateLabel.setSize(380, 30);
        dateLabel.setLocation(10,290);
        dateLabel.setForeground(Color.WHITE);
        this.getContentPane().add(dateLabel);

        JLabel statusLabel = new JLabel(applicant.format("Status:   !s"));
        statusLabel.setFont(font.deriveFont(18f));
        statusLabel.setSize(380, 30);
        statusLabel.setLocation(10,330);
        statusLabel.setForeground(Color.WHITE);
        this.getContentPane().add(statusLabel);

        JButton closeButton = new JButton("Close Entry");
        closeButton.setFocusable(false);
        closeButton.setFont(font.deriveFont(18f));
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setSize(380, 30);
        closeButton.setLocation(10,380);
        closeButton.addActionListener(e -> {
            int close = JOptionPane.showConfirmDialog(this, "Are you sure you want to close "+
                            applicant.getFirstName()+" "+applicant.getLastName()+"'s entry?", "Close",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (close == 0) {
                try {
                    HTTPRequest.getInstance().closeEntry(user, applicant);
                } catch (Exception ex) {ex.printStackTrace();}
                frame.refreshViewCs();
                this.dispose();
            }
        });
        if (applicant.getStatus().equals("CLOSED")) closeButton.setEnabled(false);
        this.getContentPane().add(closeButton);

        JButton deleteButton = new JButton("Delete Entry");
        deleteButton.setFocusable(false);
        deleteButton.setFont(font.deriveFont(18f));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setSize(380, 30);
        deleteButton.setLocation(10,420);
        deleteButton.addActionListener(e -> {
            int delete = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete "+
                    applicant.getFirstName()+" "+applicant.getLastName()+"'s entry?", "Delete",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (delete == 0) {
                try {
                    HTTPRequest.getInstance().deleteCandidate(user, applicant);
                } catch (Exception ex) {ex.printStackTrace();}
                frame.refreshViewCs();
                this.dispose();
            }
        });
        this.getContentPane().add(deleteButton);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
