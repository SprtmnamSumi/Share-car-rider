package cz.muni.fi.pv168.project.ui.validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ValidatedInputField extends JTextField implements Validable {

    private final KeyListener listener = new TypeListener();

    private final Validator<String> validator;

    public ValidatedInputField(Validator<String> validator) {
        super();
        this.addKeyListener(listener);
        this.validator = validator;
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        listener.keyReleased(null);
    }

    @Override
    public boolean evaluate() {
        return validator.evaluate(this.getText());
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
