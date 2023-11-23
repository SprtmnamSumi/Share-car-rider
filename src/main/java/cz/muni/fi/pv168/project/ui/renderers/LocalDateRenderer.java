package cz.muni.fi.pv168.project.ui.renderers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.swing.JLabel;

public class LocalDateRenderer extends AbstractRenderer<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);

    public LocalDateRenderer() {
        super(LocalDate.class);
    }

    @Override
    protected void updateLabel(JLabel label, LocalDate value) {
        label.setText(formatter.format(value));
    }
}
