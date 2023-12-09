package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.business.service.validation.Validator;
import cz.muni.fi.pv168.project.ui.validation.Validable;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ValidatedTextFieldPanel extends JPanel implements Validable {
    private final ValidatedInputField textField;

    public ValidatedTextFieldPanel(String LabelName, Validator<String> validator) {
        super();
        textField = new ValidatedInputField(validator);
        this.add(new JLabel(LabelName));
        this.add(textField);
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        this.setLayout(new GridLayout(2, 1));
    }

    @Override
    public boolean isEmpty() {
        return textField.isEmpty();
    }

    @Override
    public boolean evaluate() {
        return textField.evaluate();
    }

    public void clear() {
        textField.setText("");
    }

    public JTextField getTextField() {
        return textField;
    }
}
