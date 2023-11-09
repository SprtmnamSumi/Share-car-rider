package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.panels.Category.CategoryTablePanel;

import javax.swing.*;
import java.awt.*;

public class CategoryBar extends JPanel {
    private final JComboBox<Category> categoryJComboBox;
    private final JButton addCategoryButton;


    public CategoryBar(ListModel<Category> categoryModel, DefaultActionFactory<Category> categoryActionFactory, CategoryTableModel categoryTableModel) {
        super(new FlowLayout(FlowLayout.CENTER));

        this.categoryJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(categoryModel));
        this.categoryJComboBox.setPreferredSize(new Dimension(400, 30));

        this.addCategoryButton = new JButton("Add category");
        this.addCategoryButton.setPreferredSize(new Dimension(200, 30));

        this.addCategoryButton.addActionListener(e -> {
            CategoryTablePanel categoryTablePanel = new CategoryTablePanel(categoryTableModel, categoryActionFactory);
            var addCategory = categoryActionFactory.getAddAction(categoryTablePanel.getTable());
            addCategory.actionPerformed(e);
        });

        this.add(categoryJComboBox);
        this.add(addCategoryButton);

    }

    public Category getSelectedItem() {
        return (Category) categoryJComboBox.getSelectedItem();
    }

    public void setSelectedItem(Category category) {
        categoryJComboBox.setSelectedItem(category);
    }


}
