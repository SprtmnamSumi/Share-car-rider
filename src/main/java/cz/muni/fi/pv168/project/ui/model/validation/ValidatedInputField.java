package cz.muni.fi.pv168.project.ui.model.validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class ValidatedInputField extends JTextField implements Validable {

    private final static Color invalidColor = new Color(255,0,0,60);
    private final static Color validColor = new Color(0,255,0,60);
    private final KeyListener listener = new ValidatedInputField.TypeListener();

    public ValidatedInputField() {
        this.addKeyListener(listener);
    }

    private class TypeListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            ValidatedInputField.this.setBackground(ValidatedInputField.this.getText().isEmpty()
                    ? Color.WHITE
                    : evaluate() ? validColor : invalidColor);
        }
    }

}
