package cz.muni.fi.pv168.employees.ui.panels;

import cz.muni.fi.pv168.employees.model.Employee;
import cz.muni.fi.pv168.employees.ui.renderers.EmployeeRenderer;

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
