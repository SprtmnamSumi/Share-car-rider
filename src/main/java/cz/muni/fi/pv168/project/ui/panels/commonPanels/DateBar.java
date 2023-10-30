package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.ui.model.LocalDateModel;
import cz.muni.fi.pv168.project.ui.panels.CarRide.PlaceholderTextField;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateBar extends JPanel {
    JDatePicker tf;
    PlaceholderTextField tf1;
    PlaceholderTextField tf2;
    DateModel<LocalDate> mod = new LocalDateModel();

    public DateBar() {
        super(new FlowLayout(FlowLayout.CENTER));

        tf = new JDatePicker(mod);
        this.add(tf);

        tf1 = new PlaceholderTextField();
        tf1.setPlaceholder("hr");
        tf1.setPreferredSize(new Dimension(200, 30));
        this.add(tf1);

        tf2 = new PlaceholderTextField();
        tf2.setPlaceholder("min");
        tf2.setPreferredSize(new Dimension(200, 30));
        this.add(tf2);
    }

    public LocalDateTime getDate() {
        int minute = Integer.parseInt(tf2.getText());
        int hour = Integer.parseInt(tf1.getText());

        var day = mod.getDay();
        int month = mod.getMonth();
        int year = mod.getYear();

        LocalDateTime date = LocalDateTime.of(year, month + 1, day, hour, minute);
        return date;
    }

    public void setDate(LocalDateTime dateField) {
        LocalDate localDate = dateField.toLocalDate();
        mod.setValue(localDate);
        tf1.setText(String.valueOf(dateField.getHour()));
        tf2.setText(String.valueOf(dateField.getMinute()));
    }
}
