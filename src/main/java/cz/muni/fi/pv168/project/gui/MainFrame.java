package cz.muni.fi.pv168.project.gui;

import cz.muni.fi.pv168.project.gui.table.RideTable;
import cz.muni.fi.pv168.project.gui.filterbar.FilterBar;
import cz.muni.fi.pv168.project.gui.toolbar.MainToolBar;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class MainFrame extends JFrame {
    private final static GridBagConstraints constraints = new GridBagConstraints();

    private final static Dimension minDimension = new Dimension(320,320);
    private final static Dimension prefDimension = new Dimension(1920,1080);
    public MainFrame() throws HeadlessException {
        super();

        // Set look
        this.setMinimumSize(minDimension);
        this.setPreferredSize(prefDimension);
        this.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 0;

        // Fill
        addContents(List.of(
                new MainToolBar(),
                new FilterBar(),
                new JScrollPane(new RideTable())));
    }

    private void addContents(List<Component> components){
        for(int row = 0; row < components.size(); row++){
            constraints.gridy = row;
            if(row+1==components.size()){
                constraints.fill = GridBagConstraints.BOTH;
                constraints.weighty = 1.0;
            }
            this.add(components.get(row), constraints);
        }
    }
}
