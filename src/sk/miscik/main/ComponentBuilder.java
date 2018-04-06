package sk.miscik.main;

import sk.miscik.gui.ApplicantInfo;
import sk.miscik.gui.CustomCBUI;
import sk.miscik.gui.DateTimeDialog;
import sk.miscik.gui.LoginGUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by client on 23.02.2018.
 */
public class ComponentBuilder {

    private static JFrame frame;
    private static int viewPage = 0;
    private static Date date = null;
    private static Applicant[] applicants = new Applicant[5];

    public static void init(JFrame frame) {
        ComponentBuilder.frame = frame;
    }

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

        JButton dateButton = new JButton("Choose date");
        dateButton.setSize(250,40);
        dateButton.setLocation(530,400);
        dateButton.setFocusPainted(false);
        dateButton.setBorderPainted(false);
        dateButton.setContentAreaFilled(false);
        dateButton.setForeground(Color.WHITE);
        dateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                dateButton.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dateButton.setForeground(Color.WHITE);
            }
        });
        dateButton.setFont(font.deriveFont(30f));
        dateButton.addActionListener(e -> {
            DateTimeDialog dialog = new DateTimeDialog(frame);
            while ((date = dialog.getSelectedDate()) == null) {}

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            dateButton.setText(sdf.format(date));
        });

        Cs.add(dateButton);


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
                //CHECK IF DATE IS NULL
                if (date == null) {
                    JOptionPane.showMessageDialog(null, "Date was not set!", "Error", JOptionPane.ERROR_MESSAGE);
                //CHECK IF DATE IS AFTER TODAY
                } else if (date.getTime() < new Date().getTime()) {
                    JOptionPane.showMessageDialog(null, "Date is before today!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                    //CHECK IF DATE IS SATURDAY/SUNDAY
                    if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                        JOptionPane.showMessageDialog(null, "The chosen date is Saturday/Sunday!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        SimpleDateFormat sdfD = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm");
                        Applicant applicant = new Applicant(firstNameField.getText(), lastNameField.getText(), emailField.getText(),
                                phoneField.getText(), (String) cityBox.getSelectedItem(), (String) roomBox.getSelectedItem(),
                                (String) positionBox.getSelectedItem(), sdfD.format(date)+"T"+sdfT.format(date));
                        HTTPRequest.getInstance().submitInterview(user, applicant);

                        firstNameField.setText("");
                        lastNameField.setText("");
                        emailField.setText("");
                        phoneField.setText("");
                        dateButton.setText("Choose date");
                        date = null;
                    }
                }
            } catch (Exception ex) {ex.printStackTrace();}
        });
        Cs.add(submitButton);

        return Cs;
    }

    public static ArrayList<Component> getViewComponents(User user) {
        viewPage = 0;
        ArrayList<Component> Cs = new ArrayList<>();

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("segoeuil.ttf"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JLabel row1 = new JLabel("");
        row1.setSize(580,60);
        row1.setLocation(140,50);
        row1.setFont(font.deriveFont(14f));
        row1.setForeground(Color.WHITE);
        Cs.add(row1);

        JButton row1info = new JButton("i");
        row1info.setSize(60,60);
        row1info.setLocation(730, 50);
        row1info.setFocusPainted(false);
        row1info.setBorderPainted(true);
        row1info.setContentAreaFilled(false);
        row1info.setForeground(Color.WHITE);
        row1info.setMargin(new Insets(0,0,0,0));
        row1info.setBorder(null);
        row1info.setFont(font.deriveFont(32f));
        row1info.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                row1info.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                row1info.setForeground(Color.WHITE);
            }
        });
        row1info.addActionListener(e->new ApplicantInfo(user, applicants[0], frame));
        Cs.add(row1info);
        
        JLabel row2 = new JLabel("");
        row2.setSize(710,60);
        row2.setLocation(140,120);
        row2.setFont(font.deriveFont(14f));
        row2.setForeground(Color.WHITE);
        Cs.add(row2);

        JButton row2info = new JButton("i");
        row2info.setSize(60,60);
        row2info.setLocation(730, 120);
        row2info.setFocusPainted(false);
        row2info.setBorderPainted(true);
        row2info.setContentAreaFilled(false);
        row2info.setForeground(Color.WHITE);
        row2info.setMargin(new Insets(0,0,0,0));
        row2info.setBorder(null);
        row2info.setFont(font.deriveFont(32f));
        row2info.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                row2info.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                row2info.setForeground(Color.WHITE);
            }
        });
        row2info.addActionListener(e->new ApplicantInfo(user, applicants[1], frame));
        Cs.add(row2info);

        JLabel row3 = new JLabel("");
        row3.setSize(710,60);
        row3.setLocation(140,190);
        row3.setFont(font.deriveFont(14f));
        row3.setForeground(Color.WHITE);
        Cs.add(row3);

        JButton row3info = new JButton("i");
        row3info.setSize(60,60);
        row3info.setLocation(730, 190);
        row3info.setFocusPainted(false);
        row3info.setBorderPainted(true);
        row3info.setContentAreaFilled(false);
        row3info.setForeground(Color.WHITE);
        row3info.setMargin(new Insets(0,0,0,0));
        row3info.setBorder(null);
        row3info.setFont(font.deriveFont(32f));
        row3info.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                row3info.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                row3info.setForeground(Color.WHITE);
            }
        });
        row3info.addActionListener(e->new ApplicantInfo(user, applicants[2], frame));
        Cs.add(row3info);

        JLabel row4 = new JLabel("");
        row4.setSize(710,60);
        row4.setLocation(140,260);
        row4.setFont(font.deriveFont(14f));
        row4.setForeground(Color.WHITE);
        Cs.add(row4);

        JButton row4info = new JButton("i");
        row4info.setSize(60,60);
        row4info.setLocation(730, 260);
        row4info.setFocusPainted(false);
        row4info.setBorderPainted(true);
        row4info.setContentAreaFilled(false);
        row4info.setForeground(Color.WHITE);
        row4info.setMargin(new Insets(0,0,0,0));
        row4info.setBorder(null);
        row4info.setFont(font.deriveFont(32f));
        row4info.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                row4info.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                row4info.setForeground(Color.WHITE);
            }
        });
        row4info.addActionListener(e->new ApplicantInfo(user, applicants[3], frame));
        Cs.add(row4info);

        JLabel row5 = new JLabel("");
        row5.setSize(710,60);
        row5.setLocation(140,330);
        row5.setFont(font.deriveFont(14f));
        row5.setForeground(Color.WHITE);
        Cs.add(row5);

        JButton row5info = new JButton("i");
        row5info.setSize(60,60);
        row5info.setLocation(730, 330);
        row5info.setFocusPainted(false);
        row5info.setBorderPainted(true);
        row5info.setContentAreaFilled(false);
        row5info.setForeground(Color.WHITE);
        row5info.setMargin(new Insets(0,0,0,0));
        row5info.setBorder(null);
        row5info.setFont(font.deriveFont(32f));
        row5info.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                row5info.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                row5info.setForeground(Color.WHITE);
            }
        });
        row5info.addActionListener(e->new ApplicantInfo(user, applicants[4], frame));
        Cs.add(row5info);

        row1.setText("---");
        row2.setText("---");
        row3.setText("---");
        row4.setText("---");
        row5.setText("---");

        JLabel page = new JLabel("-");
        page.setSize(100,25);
        page.setLocation(435,410);
        page.setFont(font.deriveFont(12f));
        page.setForeground(Color.WHITE);
        Cs.add(page);

        JButton prev = new JButton("<< Prev");
        prev.setSize(100,25);
        prev.setLocation(140, 410);
        prev.addActionListener(e -> {
            try {
                viewPage--;
                Applicant[] applicants = HTTPRequest.getInstance().getInterviews(user, viewPage*5, 5);
                if (applicants == null) {
                    viewPage++;
                    ComponentBuilder.applicants[0] = null;
                    ComponentBuilder.applicants[1] = null;
                    ComponentBuilder.applicants[2] = null;
                    ComponentBuilder.applicants[3] = null;
                    ComponentBuilder.applicants[4] = null;
                    return;
                }
                String format = "!f !l | !e | !p | !c | !r";
                if (applicants[0] != null) row1.setText(applicants[0].format(format)); else row1.setText("");
                if (applicants[1] != null) row2.setText(applicants[1].format(format)); else row2.setText("");
                if (applicants[2] != null) row3.setText(applicants[2].format(format)); else row3.setText("");
                if (applicants[3] != null) row4.setText(applicants[3].format(format)); else row4.setText("");
                if (applicants[4] != null) row5.setText(applicants[4].format(format)); else row5.setText("");
                ComponentBuilder.applicants[0] = applicants[0];
                ComponentBuilder.applicants[1] = applicants[1];
                ComponentBuilder.applicants[2] = applicants[2];
                ComponentBuilder.applicants[3] = applicants[3];
                ComponentBuilder.applicants[4] = applicants[4];
                page.setText("PAGE "+(viewPage+1));
            } catch (Exception ex) {ex.printStackTrace();}
        });
        Cs.add(prev);

        JButton next = new JButton("Next >>");
        next.setSize(100,25);
        next.setLocation(690, 410);
        next.addActionListener(e -> {
            try {
                viewPage++;
                Applicant[] applicants = HTTPRequest.getInstance().getInterviews(user, viewPage*5, 5);
                if (applicants == null) {
                    viewPage--;
                    ComponentBuilder.applicants[0] = null;
                    ComponentBuilder.applicants[1] = null;
                    ComponentBuilder.applicants[2] = null;
                    ComponentBuilder.applicants[3] = null;
                    ComponentBuilder.applicants[4] = null;
                    return;
                }
                String format = "!f !l | !e | !p | !c | !r";
                if (applicants[0] != null) row1.setText(applicants[0].format(format)); else row1.setText("");
                if (applicants[1] != null) row2.setText(applicants[1].format(format)); else row2.setText("");
                if (applicants[2] != null) row3.setText(applicants[2].format(format)); else row3.setText("");
                if (applicants[3] != null) row4.setText(applicants[3].format(format)); else row4.setText("");
                if (applicants[4] != null) row5.setText(applicants[4].format(format)); else row5.setText("");
                ComponentBuilder.applicants[0] = applicants[0];
                ComponentBuilder.applicants[1] = applicants[1];
                ComponentBuilder.applicants[2] = applicants[2];
                ComponentBuilder.applicants[3] = applicants[3];
                ComponentBuilder.applicants[4] = applicants[4];
                page.setText("PAGE "+(viewPage+1));
            } catch (Exception ex) {ex.printStackTrace();}
        });
        Cs.add(next);

        try {
            Applicant[] applicants = HTTPRequest.getInstance().getInterviews(user, viewPage*5, 5);
            if (applicants != null) {
                String format = "!f !l | !e | !p | !c | !r";
                if (applicants[0] != null) row1.setText(applicants[0].format(format));
                else row1.setText("");
                if (applicants[1] != null) row2.setText(applicants[1].format(format));
                else row2.setText("");
                if (applicants[2] != null) row3.setText(applicants[2].format(format));
                else row3.setText("");
                if (applicants[3] != null) row4.setText(applicants[3].format(format));
                else row4.setText("");
                if (applicants[4] != null) row5.setText(applicants[4].format(format));
                else row5.setText("");

                ComponentBuilder.applicants[0] = applicants[0];
                ComponentBuilder.applicants[1] = applicants[1];
                ComponentBuilder.applicants[2] = applicants[2];
                ComponentBuilder.applicants[3] = applicants[3];
                ComponentBuilder.applicants[4] = applicants[4];
            } else {
                ComponentBuilder.applicants[0] = null;
                ComponentBuilder.applicants[1] = null;
                ComponentBuilder.applicants[2] = null;
                ComponentBuilder.applicants[3] = null;
                ComponentBuilder.applicants[4] = null;
            }
            page.setText("PAGE "+(viewPage+1));
        } catch (Exception ex) {ex.printStackTrace();}

        return Cs;
    }

}
