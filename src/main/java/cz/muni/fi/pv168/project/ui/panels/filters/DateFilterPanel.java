package cz.muni.fi.pv168.project.ui.panels.filters;

import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.SpinnerDatePanel;

import javax.swing.*;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateFilterPanel extends FilterPanel {

    private final SpinnerDatePanel dateFromPanel;
    private final SpinnerDatePanel dateToPanel;
    private final CarRideTableFilter filter;

    public DateFilterPanel(CarRideTableFilter filter) {
        super();
        this.filter = filter;
        dateFromPanel = new SpinnerDatePanel("Date from");
        dateToPanel = new SpinnerDatePanel("Date to");

        dateFromPanel.getSpinnerDate().addChangeListener(change -> refreshFilter());
        dateToPanel.getSpinnerDate().addChangeListener(change -> refreshFilter());

        this.add(dateFromPanel);
        this.add(dateToPanel);
    }

    @Override
    public void reset() {
        dateFromPanel.setSpinnerDate(Date.from(Instant.ofEpochSecond(0)));
        dateToPanel.setSpinnerDate(Date.from(Instant.now().plusSeconds(TimeUnit.DAYS.toSeconds(1))));
        refreshFilter();
    }

    private void refreshFilter() {
        filter.filterByDate(
                (Date) dateFromPanel.getSpinnerDate().getValue(),
                (Date) dateToPanel.getSpinnerDate().getValue());
    }
}
