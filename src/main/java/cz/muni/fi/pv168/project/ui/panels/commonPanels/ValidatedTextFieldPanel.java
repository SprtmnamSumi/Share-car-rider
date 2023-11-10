package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.ui.model.validation.Validable;
import cz.muni.fi.pv168.project.ui.model.validation.ValidatedInputField;

import javax.swing.*;
import java.awt.*;

public abstract class ValidatedTextFieldPanel extends JPanel implements Validable {
    private final JTextField textField = new ValidatedInputField() {
        @Override
        public boolean evaluate() {
            return ValidatedTextFieldPanel.this.evaluate();
        }
    };

    public ValidatedTextFieldPanel(String LabelName) {
        super();
        this.add(new JLabel(LabelName));
        this.add(textField);
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        this.setLayout(new GridLayout(2, 1));
    }

    public void clear(){
        textField.setText("");
        evaluate();
    }

    public JTextField getTextField() {
        return textField;
    }
}
