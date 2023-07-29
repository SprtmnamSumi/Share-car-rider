package cz.muni.fi.pv168.employees.ui.renderers;

import cz.muni.fi.pv168.employees.model.Department;

import javax.swing.JLabel;

public class DepartmentRenderer extends AbstractRenderer<Department> {

    public DepartmentRenderer() {
        super(Department.class);
    }

    @Override
    protected void updateLabel(JLabel label, Department value) {
        label.setText(String.format("%s: %s", value.getNumber(), value.getName()));
    }
}