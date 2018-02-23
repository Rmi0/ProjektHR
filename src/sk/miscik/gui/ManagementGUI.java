package sk.miscik.gui;

import sk.miscik.main.Main;
import sk.miscik.main.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ManagementGUI extends JFrame {

    public static final int WIDTH = 800, HEIGHT = 720;

    private User user;
    private JPanel dashboardPanel;
    private JPanel newPanel;
    private JPanel myPanel;
    private Point mouse;

    public ManagementGUI(User user) throws Exception {
        super("ProjektHR Managment");
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
        this.getContentPane().add(sidePanel);

        this.dashboardPanel = new DashboardPanel(user);
        this.getContentPane().add(dashboardPanel);
        //this.setContentPane(dashboardPanel);

        this.pack();
        this.setLocationRelativeTo(null);
    }

}
