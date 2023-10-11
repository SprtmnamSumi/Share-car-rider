package cz.muni.fi.pv168.project.gui;

import cz.muni.fi.pv168.project.gui.panels.CarRidesTablePanel;
import cz.muni.fi.pv168.project.gui.table.RideTable;
import cz.muni.fi.pv168.project.gui.filterbar.FilterBar;
import cz.muni.fi.pv168.project.gui.table.RideTableModel;
import cz.muni.fi.pv168.project.gui.toolbar.MainToolBar;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.util.List;


public class MainFrame extends JFrame {
    private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();;
    private final static Dimension minDimension = new Dimension(320,320);
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final JFrame frame;
    public MainFrame() throws HeadlessException {
        frame = createFrame();
        //super();
        var rideTable = new RideTable(new Dimension(0, 1));
        var carRidesTablePanel = new CarRidesTablePanel(rideTable);

        // Set look
        this.setMinimumSize(minDimension);
        this.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;

        // Fill
        addContents(List.of(
                new MainToolBar(new Dimension(50,40)),
                new FilterBar(new Dimension(screenSize.width,100))));
                //new JScrollPane(new RideTable(screenSize))));

        var tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Car Rides", carRidesTablePanel);

        frame.add(tabbedPane, BorderLayout.CENTER);
    }

    private JFrame createFrame() {
        var frame = new JFrame("Ride records");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
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
