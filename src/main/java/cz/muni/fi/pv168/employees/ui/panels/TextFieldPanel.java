package cz.muni.fi.pv168.employees.ui.panels;

import javax.swing.*;

public class TextFieldPanel extends cz.muni.fi.pv168.employees.ui.panels.FilterComponentPanel {
    private final JTextField textField = new JTextField();

    public TextFieldPanel(String LabelName) {
        super(LabelName);
        this.add(textField);
    }

    public JTextField getTextField() {
        return textField;
    }
}
