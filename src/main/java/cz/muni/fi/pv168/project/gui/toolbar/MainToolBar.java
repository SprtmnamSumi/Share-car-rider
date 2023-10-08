package cz.muni.fi.pv168.project.gui.toolbar;

import javax.swing.*;
import java.awt.*;

/**
 * Class instance of main toolbar with predefined actions
 */
public class MainToolBar extends JToolBar {
    public MainToolBar(Dimension dimension) {
        super();
        this.setPreferredSize(dimension);
        this.setFloatable(false);
        this.addActionButton(new JButton("File"));
        this.addActionButton(new JButton("Insert"));
        this.addActionButton(new JButton("Update"));
        this.addActionButton(new JButton("Delete"));
        this.addActionButton(new JButton("Filter"));
        this.addActionButton(new JButton("Stats"));
    }
    void addActionButton(Component button) {
        button.setPreferredSize(this.getPreferredSize());
        this.add(button);
    }
}
