package sk.miscik.gui;

import sk.miscik.main.User;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {

    private User user;

    public ContentPanel(User user) {
        this.setMinimumSize(new Dimension(ManagementGUI.WIDTH-130, ManagementGUI.HEIGHT-40));
        this.setPreferredSize(new Dimension(ManagementGUI.WIDTH-130, ManagementGUI.HEIGHT-40));
        this.setMaximumSize(new Dimension(ManagementGUI.WIDTH-130, ManagementGUI.HEIGHT-40));
        this.setLocation(130,40);
        this.setBackground(new Color(57,57,71));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
