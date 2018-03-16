package sk.miscik.gui;

import sk.miscik.main.ComponentBuilder;
import sk.miscik.main.HTTPRequest;
import sk.miscik.main.Main;
import sk.miscik.main.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class ManagementGUI extends JFrame {

    public static final int WIDTH = 800, HEIGHT = 720;

    private User user;
    private ArrayList<Component> dashboardComponents;
    private ArrayList<Component> newComponents;
    private ArrayList<Component> viewComponents;
    private Point mouse;

    public ManagementGUI(User user) throws Exception {
        super("ProjektHR Managment");

        HTTPRequest.getInstance().getCities(user);

        Font font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("segoeuil.ttf"));

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().setBackground(new Color(57,57,71));
        this.getContentPane().setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setLayout(null);
        this.getContentPane().setLayout(null);
        this.user = user;

        this.dashboardComponents = ComponentBuilder.getDashboardComponents(user);
        this.newComponents = ComponentBuilder.getNewComponents(user);
        this.viewComponents = ComponentBuilder.getViewComponents(user);

        for (Component c : dashboardComponents) {
            this.add(c);
        }

        JLabel topLabel = new JLabel("  "+this.getTitle());
        topLabel.setSize(WIDTH,40);
        topLabel.setLocation(0,0);
        topLabel.setBackground(new Color(48,48,67));
        topLabel.setForeground(Color.WHITE);
        topLabel.setFont(font.deriveFont(24f));
        topLabel.setOpaque(true);
        topLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouse = new Point(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouse = null;
            }
        });
        topLabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mouse != null) {
                    setLocation((int)(e.getXOnScreen()-mouse.getX()), (int)(e.getYOnScreen()-mouse.getY()));
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (mouse != null) {
                    setLocation((int)(e.getXOnScreen()-mouse.getX()), (int)(e.getYOnScreen()-mouse.getY()));
                }
            }
        });
        this.getContentPane().add(topLabel);

        JButton exitButton = new JButton("X");
        exitButton.setSize(40,40);
        exitButton.setLocation(WIDTH-40, 0);
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setForeground(Color.WHITE);
        exitButton.setMargin(new Insets(0,0,0,0));
        exitButton.setBorder(null);
        exitButton.setFont(font.deriveFont(30f));
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setForeground(Color.WHITE);
            }
        });
        exitButton.addActionListener(e -> System.exit(0));
        topLabel.add(exitButton);

        JPanel sidePanel = new JPanel();
        sidePanel.setSize(130,HEIGHT-40);
        sidePanel.setLocation(0,40);
        sidePanel.setBackground(new Color(48,48,67));
        sidePanel.setLayout(null);

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.setSize(130,40);
        dashboardButton.setLocation(0, 0);
        dashboardButton.setFocusPainted(false);
        dashboardButton.setBorderPainted(false);
        dashboardButton.setContentAreaFilled(false);
        dashboardButton.setForeground(Color.WHITE);
        dashboardButton.setMargin(new Insets(0,0,0,0));
        dashboardButton.setBorder(null);
        dashboardButton.setFont(font.deriveFont(24f));
        dashboardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                dashboardButton.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dashboardButton.setForeground(Color.WHITE);
            }
        });
        dashboardButton.addActionListener(e ->{
            for (Component c : newComponents) {
                this.getContentPane().remove(c);
            }
            for (Component c : viewComponents) {
                this.getContentPane().remove(c);
            }
            for (Component c : dashboardComponents) {
                this.getContentPane().add(c);
            }
            this.getContentPane().revalidate();
            this.getContentPane().repaint();
        });
        sidePanel.add(dashboardButton);

        JButton newButton = new JButton("New");
        newButton.setSize(130,40);
        newButton.setLocation(0, 40);
        newButton.setFocusPainted(false);
        newButton.setBorderPainted(false);
        newButton.setContentAreaFilled(false);
        newButton.setForeground(Color.WHITE);
        newButton.setMargin(new Insets(0,0,0,0));
        newButton.setBorder(null);
        newButton.setFont(font.deriveFont(24f));
        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newButton.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newButton.setForeground(Color.WHITE);
            }
        });
        newButton.addActionListener(e ->{
            for (Component c : dashboardComponents) {
                this.getContentPane().remove(c);
            }
            for (Component c : viewComponents) {
                this.getContentPane().remove(c);
            }
            for (Component c : newComponents) {
                this.getContentPane().add(c);
            }
            this.getContentPane().revalidate();
            this.getContentPane().repaint();
        });
        sidePanel.add(newButton);

        JButton viewButton = new JButton("View");
        viewButton.setSize(130,40);
        viewButton.setLocation(0, 80);
        viewButton.setFocusPainted(false);
        viewButton.setBorderPainted(false);
        viewButton.setContentAreaFilled(false);
        viewButton.setForeground(Color.WHITE);
        viewButton.setMargin(new Insets(0,0,0,0));
        viewButton.setBorder(null);
        viewButton.setFont(font.deriveFont(24f));
        viewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                viewButton.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                viewButton.setForeground(Color.WHITE);
            }
        });
        viewButton.addActionListener(e ->{
            for (Component c : dashboardComponents) {
                this.getContentPane().remove(c);
            }
            for (Component c : newComponents) {
                this.getContentPane().remove(c);
            }
            for (Component c : viewComponents) {
                this.getContentPane().add(c);
            }
            this.getContentPane().revalidate();
            this.getContentPane().repaint();
        });
        sidePanel.add(viewButton);

        this.getContentPane().add(sidePanel);

        this.pack();
        this.setLocationRelativeTo(null);
    }

}
