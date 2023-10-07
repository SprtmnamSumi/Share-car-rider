package cz.muni.fi.pv168.project.gui.filterbar.panels;

import javax.swing.*;
import java.awt.*;

public class TextFieldPanel extends FilterComponentPanel{
    private JTextField textField = new JTextField();
    public TextFieldPanel(String LabelName) {
        super(LabelName);
        this.add(textField);
    }

    public JTextField getTextField() {
        return textField;
    }
}
