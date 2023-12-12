package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.validation.Validable;
import cz.muni.fi.pv168.project.ui.validation.ValidableListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListModel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;

public class CategoryBar extends JPanel implements Validable {
    private final JComboBox<Category> categoryJComboBox;
    private final JButton addCategoryButton;


    public CategoryBar(ListModel<Category> categoryModel,
                       DefaultActionFactory<Category> categoryActionFactory,
                       TableModel<Category> categoryTableModel,
                       ValidableListener validableListener) {
        super(new FlowLayout(FlowLayout.CENTER));

        this.categoryJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(categoryModel));
        this.categoryJComboBox.setPreferredSize(new Dimension(400, 30));
        this.categoryJComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                validableListener.fireChange();
            }
        });

        this.addCategoryButton = new JButton("Add category");
        this.addCategoryButton.setPreferredSize(new Dimension(200, 30));

        this.addCategoryButton.addActionListener(e -> {
            categoryActionFactory.getAddAction(new JTable(categoryTableModel)).actionPerformed(e);
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


    @Override
    public boolean evaluate() {
        return !isEmpty();
    }

    @Override
    public boolean isEmpty() {
        return getSelectedItem() == null;
    }
}
