package cz.muni.fi.pv168.project.ui.validation;

import java.awt.Color;
import java.awt.event.KeyListener;

public interface Validable {
    Color INVALID_COLOR = new Color(255, 0, 0, 60);
    Color VALID_COLOR = new Color(0, 255, 0, 60);

    boolean evaluate();

    boolean isEmpty();

    void addKeyListener(KeyListener listener);
}
