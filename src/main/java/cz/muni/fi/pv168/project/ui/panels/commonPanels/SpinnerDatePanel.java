package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class SpinnerDatePanel extends FilterComponentPanel {
    private final JSpinner spinnerDate = new JSpinner(new SpinnerDateModel());

    public SpinnerDatePanel(String labelName) {
        super(labelName);
        this.add(spinnerDate);
    }

    public JSpinner getSpinnerDate() {
        return spinnerDate;
    }

    public void setSpinnerDate(Date date) {
        spinnerDate.setValue(date);
    }
}
