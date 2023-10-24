package cz.muni.fi.pv168.project.ui.panels.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.renderers.CategoryRenderer;

import javax.swing.*;
import java.awt.*;


public class CategoryListPanel extends JPanel {

    public CategoryListPanel(ListModel<Category> categoryListModel) {
        var list = new JList<>(categoryListModel);
        list.setCellRenderer(new CategoryRenderer());
        setLayout(new BorderLayout());
        add(new JScrollPane(list));
    }
}
