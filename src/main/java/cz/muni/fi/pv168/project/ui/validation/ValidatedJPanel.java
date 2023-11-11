package cz.muni.fi.pv168.project.ui.validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class ValidatedJPanel extends JPanel implements Validable {
    private ValidatedInputField[] validables;

    public ValidatedJPanel(ValidatedInputField... validables) {
        super(new FlowLayout(FlowLayout.CENTER));
        this.validables = validables;
    }

    public void setValidables(ValidatedInputField... validables) {
        this.removeAll();
        this.validables = validables;
        Arrays.stream(validables).forEach(this::add);
    }

    @Override
    public boolean evaluate() {
        return Arrays.stream(validables).toList().stream().allMatch(ValidatedInputField::evaluate);
    }

    @Override
    public boolean isEmpty() {
        return Arrays.stream(validables).toList().stream().anyMatch(ValidatedInputField::isEmpty);
    }

    @Override
    public void addKeyListener(KeyListener listener) {
        Arrays.stream(validables).forEach(v -> v.addKeyListener(listener));
    }
}
