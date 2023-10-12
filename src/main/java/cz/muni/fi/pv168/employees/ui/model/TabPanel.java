package cz.muni.fi.pv168.employees.ui.model;

import javax.swing.*;

public class TabPanel extends JTabbedPane {
    private int index = 0;

    public TabPanel() {
        super();
    }

    public void addTab(String title, JPanel panel) {
        this.add(title, panel);
        index++;
    }

    public void addSpecialTab(String title, JPanel panel, JPanel button) {
        this.add(title, panel);
        this.setTabComponentAt(index++, button);
    }
}
