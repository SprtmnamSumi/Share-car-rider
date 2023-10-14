package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import javax.swing.*;
import java.awt.*;

public class FilterComponentPanel extends JPanel {
    public FilterComponentPanel(String labelName) {
        super();
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        this.add(new JLabel(labelName));
        this.setLayout(new GridLayout(2, 1));
    }
}
