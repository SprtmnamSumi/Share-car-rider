package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import javax.swing.*;
import java.awt.*;

public class TextFieldPanel extends JPanel {
    private final JTextField textField = new JTextField();

    public TextFieldPanel(String LabelName) {
        super();
        this.add(new JLabel(LabelName));
        this.add(textField);
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        this.setLayout(new GridLayout(2, 1));
    }

    public JTextField getTextField() {
        return textField;
    }
}
