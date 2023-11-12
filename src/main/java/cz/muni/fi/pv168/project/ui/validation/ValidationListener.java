package cz.muni.fi.pv168.project.ui.validation;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class ValidationListener {

    private final KeyListener listener = new KeyListener();
    private final List<Validable> validables;

    public ValidationListener(Validable... validables) {
        this.validables = new LinkedList<>();
        this.validables.addAll(Arrays.stream(validables).toList());
        this.validables.forEach(v -> v.addKeyListener(listener));
    }

    public void setValidables(Validable... validables) {
        this.validables.addAll(Arrays.asList(validables));
        this.validables.forEach(v -> v.addKeyListener(listener));
    }

    public void fireChange() {
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
