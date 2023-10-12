package cz.muni.fi.pv168.employees.ui.model;

import cz.muni.fi.pv168.employees.model.Category;

import javax.swing.*;
import java.util.List;

public class CategoryListModel extends AbstractListModel<Category> {

    private final List<Category> departments;

    public CategoryListModel(List<Category> departments) {
        this.departments = departments;
    }

    @Override
    public int getSize() {
        return departments.size();
    }

    @Override
    public Category getElementAt(int index) {
        return departments.get(index);
    }
}
