package sk.miscik.main;

import sk.miscik.gui.LoginGUI;

import javax.swing.*;
/**
 * Created by client on 09.02.2018.
 */
public class Main {

    public static final LookAndFeel defaultLAF = UIManager.getLookAndFeel();

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new LoginGUI().setVisible(true);
    }
}
