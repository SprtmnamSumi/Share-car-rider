package cz.muni.fi.pv168.project.ui.validation;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class ValidationListener extends KeyAdapter {
    private final List<Validable> validables = new LinkedList<>();

    public void setListeners(Validable ... validables){
        Arrays.stream(validables).toList().forEach(v -> v.addKeyListener(this));
        this.validables.addAll(Arrays.asList(validables));
    }

    public void fireChange() {
        this.keyReleased(null);
    }

    protected abstract void onChange(boolean isValid);

    @Override
    public void keyReleased(KeyEvent e) {
            onChange(validables.parallelStream().allMatch(Validable::evaluate));
        }
}
