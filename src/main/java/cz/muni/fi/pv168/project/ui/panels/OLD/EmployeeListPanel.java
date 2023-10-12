package cz.muni.fi.pv168.project.ui.panels.OLD;

import cz.muni.fi.pv168.project.entities.old.Employee;
import cz.muni.fi.pv168.project.ui.renderers.OLD.EmployeeRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with employee records in a list.
 */
public class EmployeeListPanel extends JPanel {

    public EmployeeListPanel(ListModel<Employee> employeeListModel) {
        var list = new JList<>(employeeListModel);
        list.setCellRenderer(new EmployeeRenderer());
        setLayout(new BorderLayout());
        add(new JScrollPane(list));
    }
}
