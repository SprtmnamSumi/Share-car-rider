package cz.muni.fi.pv168.project.gui.toolbar;

import javax.swing.*;
import java.awt.*;

public class MainToolBar extends JToolBar {
    private final static Dimension DEFAULT_DIMENSION = new Dimension(50,50);
    public MainToolBar() {
        super();
        this.setPreferredSize(DEFAULT_DIMENSION);
        this.setFloatable( false);
        this.addActionButton(new JButton("File"));
        this.addActionButton(new JButton("Edit"));
        this.addActionButton(new JButton("Format"));
        this.addActionButton(new JButton("View"));
        this.addActionButton(new JButton("Help"));
    }
    void addActionButton(Component button) {
        button.setPreferredSize(DEFAULT_DIMENSION);
        this.add(button);
    }
}
