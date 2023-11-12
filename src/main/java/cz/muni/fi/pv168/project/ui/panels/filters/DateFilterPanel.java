package cz.muni.fi.pv168.project.ui.panels.filters;

import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.SpinnerDatePanel;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateFilterPanel extends FilterPanel {

    private final SpinnerDatePanel dateFromPanel;
    private final SpinnerDatePanel dateToPanel;
    private final ICarRideTableFilter filter;

    public DateFilterPanel(ICarRideTableFilter filter) {
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
        dateFromPanel.setSpinnerDate(
                Date.from(Instant.now().minusSeconds(TimeUnit.DAYS.toSeconds(30))));
        dateToPanel.setSpinnerDate(
                Date.from(Instant.now().plusSeconds(TimeUnit.DAYS.toSeconds(1))));
        refreshFilter();
    }

    private void refreshFilter() {
        filter.filterByDate(
                (Date) dateFromPanel.getSpinnerDate().getValue(),
                (Date) dateToPanel.getSpinnerDate().getValue());
    }
}
