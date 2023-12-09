package cz.muni.fi.pv168.project.ui.validation;

import cz.muni.fi.pv168.project.business.service.validation.Validator;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class ValidatedInputField extends JTextField implements Validable {

    private final KeyListener listener = new TypeListener();

    private final Validator<String> validator;

    @SafeVarargs
    public ValidatedInputField(Validator<String>... validators) {
        super();
        this.addKeyListener(listener);
        this.validator = Validator.compose(Arrays.stream(validators).toList());
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        listener.keyReleased(null);
    }

    @Override
    public boolean evaluate() {
        return validator.validate(this.getText()).isValid();
    }

    @Override
    public boolean isEmpty() {
        return this.getText().isEmpty();
    }

    class TypeListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            ValidatedInputField.this.setBackground(ValidatedInputField.this.isEmpty()
                    ? Color.WHITE
                    : ValidatedInputField.this.evaluate() ? Color.WHITE : Validable.INVALID_COLOR);
        }
    }
}
