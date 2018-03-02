package sk.miscik.gui;

import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

/**
 * Created by client on 02.03.2018.
 */
public class CustomCBUI extends BasicComboBoxUI {

    @Override
    protected JButton createArrowButton() {
        return new BasicArrowButton(BasicArrowButton.SOUTH, new Color(48,48,67), new Color(48,48,67), Color.WHITE, new Color(48,48,67));
    }
}
