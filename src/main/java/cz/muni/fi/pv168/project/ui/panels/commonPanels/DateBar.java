package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.ui.panels.CarRide.PlaceholderTextField;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class DateBar extends JPanel {


    public DateBar(DateModel<LocalDate> dateField) {
        super(new FlowLayout(FlowLayout.CENTER));
        TestDataGenerator testDataGenerator = new TestDataGenerator();


        //        // Set look
//                this.setMaximumSize(dimension);
        var x = new JTextField();


        var tf = new JDatePicker(dateField);
        this.add(tf);


        var tf1 = new PlaceholderTextField();
        tf1.setPlaceholder("hr");
        tf1.setPreferredSize(new Dimension(200, 30));
        this.add(tf1);

        var tf2 = new PlaceholderTextField();
        tf2.setPlaceholder("min");
        tf2.setPreferredSize(new Dimension(200, 30));
        this.add(tf2);


    }
}
