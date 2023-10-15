package cz.muni.fi.pv168.project.ui.panels.Category;

import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.ui.renderers.CategoryRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with employee records in a list.
 */
public class CategoryListPanel extends JPanel {

    public CategoryListPanel(ListModel<Category> employeeListModel) {
        var list = new JList<>(employeeListModel);
        list.setCellRenderer(new CategoryRenderer());
        setLayout(new BorderLayout());
        add(new JScrollPane(list));
    }
}
