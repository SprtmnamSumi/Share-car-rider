package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.ui.validation.Validable;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;
import cz.muni.fi.pv168.project.ui.validation.ValidatorFactory;

import javax.swing.*;
import java.awt.*;

public class ValidatedTextFieldPanel extends JPanel implements Validable {
    private final ValidatedInputField textField = new ValidatedInputField(ValidatorFactory.intValidator());

    public ValidatedTextFieldPanel(String LabelName) {
        super();
        this.add(new JLabel(LabelName));
        this.add(textField);
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        this.setLayout(new GridLayout(2, 1));
    }

    @Override
    public boolean isEmpty(){
        return textField.isEmpty();
    }

    @Override
    public boolean evaluate() {
        return textField.evaluate();
    }

    public void clear(){
        textField.setText("");
    }

    public JTextField getTextField() {
        return textField;
    }
}
