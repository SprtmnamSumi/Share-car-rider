package cz.muni.fi.pv168.project.ui.model.validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public interface Validable{
    public final static Color INVALID_COLOR = new Color(255,0,0,60);
    public final static Color VALID_COLOR = new Color(0,255,0,60);
    boolean evaluate();

    boolean isEmpty();
}
