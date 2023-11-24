package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
