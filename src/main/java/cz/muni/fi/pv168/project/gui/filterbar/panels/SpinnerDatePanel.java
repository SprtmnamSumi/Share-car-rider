package cz.muni.fi.pv168.project.gui.filterbar.panels;

import javax.swing.*;

public class SpinnerDatePanel extends FilterComponentPanel{
    private final JSpinner spinnerDate = new JSpinner(new SpinnerDateModel());

    public SpinnerDatePanel(String labelName) {
        super(labelName);
        this.add(spinnerDate);
    }

    public JSpinner getSpinnerDate() {
        return spinnerDate;
    }
}
