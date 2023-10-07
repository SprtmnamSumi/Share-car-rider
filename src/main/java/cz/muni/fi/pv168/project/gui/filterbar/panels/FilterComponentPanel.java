package cz.muni.fi.pv168.project.gui.filterbar.panels;

import javax.swing.*;
import java.awt.*;

public class FilterComponentPanel extends JPanel {
    public FilterComponentPanel(String labelName) {
        super();
        this.add(new JLabel(labelName));
        this.setLayout(new GridLayout(2, 1));
    }
}
