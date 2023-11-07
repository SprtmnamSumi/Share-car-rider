package cz.muni.fi.pv168.project.ui.panels.filters;

import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.SpinnerDatePanel;

import javax.swing.*;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class DateFilterPanel extends JPanel {

    SpinnerDatePanel dateFromPanel;
    SpinnerDatePanel dateToPanel;
    private final CarRideTableFilter filter;
    public DateFilterPanel(CarRideTableFilter filter){
        super();
        this.filter = filter;
        dateFromPanel = new SpinnerDatePanel("Date from");
        dateToPanel = new SpinnerDatePanel("Date to");

        dateFromPanel.getSpinnerDate().addChangeListener(change -> refreshDateFilter(dateFromPanel, dateToPanel));
        dateToPanel.getSpinnerDate().addChangeListener(change -> refreshDateFilter(dateFromPanel, dateToPanel));

        this.add(dateFromPanel);
        this.add(dateToPanel);
    }

    public void reset(){
        dateFromPanel.setSpinnerDate(Time.valueOf(LocalDateTime.MIN.toLocalTime()));
        dateToPanel.setSpinnerDate(Time.valueOf(LocalDateTime.now().toLocalTime()));
    }

    private void refreshDateFilter(SpinnerDatePanel from, SpinnerDatePanel to){
        filter.filterByTime((Date) from.getSpinnerDate().getValue(), (Date) to.getSpinnerDate().getValue());
    }
}
