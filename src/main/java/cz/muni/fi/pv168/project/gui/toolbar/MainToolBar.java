package cz.muni.fi.pv168.project.gui.toolbar;

import javax.swing.*;
import java.awt.*;

/**
 * Class instance of main toolbar with predefined actions
 */
public class MainToolBar extends JToolBar {
    private final static Dimension DEFAULT_DIMENSION = new Dimension(50,50);
    public MainToolBar() {
        super();
        this.setPreferredSize(DEFAULT_DIMENSION);
        this.setFloatable(false);
        this.addActionButton(new JButton("File"));
        this.addActionButton(new JButton("Insert"));
        this.addActionButton(new JButton("Update"));
        this.addActionButton(new JButton("Delete"));
        this.addActionButton(new JButton("Filter"));
        this.addActionButton(new JButton("Stats"));
    }
    void addActionButton(Component button) {
        button.setPreferredSize(DEFAULT_DIMENSION);
        this.add(button);
    }
}
