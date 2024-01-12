package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.business.service.validation.common.ValidatorFactory;
import cz.muni.fi.pv168.project.ui.model.LocalDateModel;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;
import cz.muni.fi.pv168.project.ui.validation.ValidatedJPanel;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateBar extends ValidatedJPanel {
    final JDatePicker date;
    final ValidatedInputField hoursField = new ValidatedInputField(ValidatorFactory.intValidator(0, 24));
    final ValidatedInputField minutesField = new ValidatedInputField(ValidatorFactory.intValidator(0, 60));
    final DateModel<LocalDate> mod = new LocalDateModel();

    public DateBar() {
        super();
        this.setValidables(hoursField, minutesField);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        date = new JDatePicker(mod);
        this.add(date);

        hoursField.setText("hr");
        hoursField.setPreferredSize(new Dimension(200, 30));
        this.add(hoursField);

        minutesField.setText("min");
        minutesField.setPreferredSize(new Dimension(200, 30));
        this.add(minutesField);
    }

    public LocalDateTime getDate() {
        int minute = Integer.parseInt(minutesField.getText());
        int hour = Integer.parseInt(hoursField.getText());

        var day = mod.getDay();
        int month = mod.getMonth();
        int year = mod.getYear();

        return LocalDateTime.of(year, month + 1, day, hour, minute);
    }

    public void setDate(LocalDateTime dateField) {
        LocalDate localDate = dateField.toLocalDate();
        mod.setValue(localDate);
        hoursField.setText(String.valueOf(dateField.getHour()));
        minutesField.setText(String.valueOf(dateField.getMinute()));
    }
}
