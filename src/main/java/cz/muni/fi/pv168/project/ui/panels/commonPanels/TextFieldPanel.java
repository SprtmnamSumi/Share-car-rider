package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import javax.swing.*;

public class TextFieldPanel extends FilterComponentPanel {
    private final JTextField textField = new JTextField();

    public TextFieldPanel(String LabelName) {
        super(LabelName);
        this.add(textField);
    }

    public JTextField getTextField() {
        return textField;
    }
}
