package cz.muni.fi.pv168.project.gui.tabs;

import javax.swing.*;
import java.awt.*;

public class TabbedPane extends JTabbedPane {
    public TabbedPane() {
        super();
        this.addTab("TAB1", new Container());
        this.addTab("TAB2", new Container());
        this.addTab("TAB3", new Container());
    }
}
