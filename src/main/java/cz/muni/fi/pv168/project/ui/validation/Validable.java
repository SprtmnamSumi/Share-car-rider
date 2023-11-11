package cz.muni.fi.pv168.project.ui.validation;

import java.awt.*;

public interface Validable{
    public final static Color INVALID_COLOR = new Color(255,0,0,60);
    public final static Color VALID_COLOR = new Color(0,255,0,60);
    boolean evaluate();

    boolean isEmpty();
}
