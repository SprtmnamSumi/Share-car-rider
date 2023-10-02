package cz.muni.fi.pv168.project.gui;

import cz.muni.fi.pv168.project.gui.tabs.TabbedPane;
import cz.muni.fi.pv168.project.gui.toolbar.MainToolBar;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private final static Dimension minDimension = new Dimension(320,320);
    private final static Dimension prefDimension = new Dimension(1920,1080);
    public MainFrame() throws HeadlessException {
        super();
        this.setMinimumSize(minDimension);
        this.setPreferredSize(prefDimension);
        this.add(new MainToolBar(), BorderLayout.PAGE_START);
        this.add(new TabbedPane(), BorderLayout.CENTER);
    }
}
