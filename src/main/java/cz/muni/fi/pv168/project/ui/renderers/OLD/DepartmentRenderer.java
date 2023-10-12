package cz.muni.fi.pv168.project.ui.renderers.OLD;

import cz.muni.fi.pv168.project.entities.old.Department;
import cz.muni.fi.pv168.project.ui.renderers.AbstractRenderer;

import javax.swing.*;

public class DepartmentRenderer extends AbstractRenderer<Department> {

    public DepartmentRenderer() {
        super(Department.class);
    }

    @Override
    protected void updateLabel(JLabel label, Department value) {
        label.setText(String.format("%s: %s", value.getNumber(), value.getName()));
    }
}
