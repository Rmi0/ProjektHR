package sk.miscik.gui;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.privatejgoodies.forms.layout.ConstantSize;
import sk.miscik.main.Main;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.util.Date;

public class DateTimeDialog extends JDialog {

    private Date date;

    public DateTimeDialog(Frame frame) {
        super(frame,"Choose date" ,true);
        this.date = new Date();

        this.setResizable(false);
        this.getContentPane().setMinimumSize(new Dimension(450,70));
        this.getContentPane().setPreferredSize(new Dimension(450,70));
        this.getContentPane().setMaximumSize(new Dimension(450,70));
        this.getContentPane().setBackground(new Color(57,57,71));
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        DateTimePicker picker = new DateTimePicker();
        picker.setDateTimeStrict(LocalDateTime.now());
        picker.setSize(430, 50);
        picker.setLocation(10,10);
        picker.setGapSize(10, ConstantSize.PIXEL);
        picker.setBackground(new Color(0,0,0,0));
        picker.addDateTimeChangeListener(dateTimeChangeEvent -> {
            ZonedDateTime zdt = picker.getDateTimeStrict().atZone(ZoneId.of("Europe/Bratislava"));
            this.date = new Date(zdt.toInstant().toEpochMilli());
        });
        this.getContentPane().add(picker);

        this.pack();
        this.setVisible(true);
    }

    public Date getSelectedDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
