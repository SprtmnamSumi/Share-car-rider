package cz.muni.fi.pv168.project.ui.validation;

import cz.muni.fi.pv168.project.ui.dialog.TemplateDialog;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public abstract class ValidationListener {

    private final List<ValidatedInputField> validables;

    private final KeyListener listener = new KeyListener();

    public ValidationListener(ValidatedInputField... validables) {
        this.validables = Arrays.stream(validables).toList();
        this.validables.forEach(v -> v.addKeyListener(listener));
    }

    public void fireChange(){
        listener.keyReleased(null);
    }

    protected abstract void onChange(boolean isValid);

    private class KeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            onChange(validables.parallelStream().allMatch(Validable::evaluate));
        }
    }
}
