package cz.muni.fi.pv168.project.ui.panels.filters;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.filters.Filters;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.ComboBoxPanel;

public class CategoryFilterPanel extends FilterPanel {

    private final ComboBoxPanel<Category> comboBoxPanel;
    private final CarRideTableFilter filter;

    private final CategoryTableModel categories;

    public CategoryFilterPanel(CarRideTableFilter filter, CategoryTableModel categories) {
        super();
        this.comboBoxPanel = new ComboBoxPanel<>("Category");
        this.categories = categories;
        this.filter = filter;
        this.add(comboBoxPanel);
        comboBoxPanel.getComboBox().addItemListener((e) -> refreshFilter());
    }

    @Override
    public void reset() {
        comboBoxPanel.getComboBox().setSelectedItem(null);
    }

    @Override
    public void updateValues() {
        Object selection = comboBoxPanel.getComboBox().getSelectedItem();
        comboBoxPanel.setComboBoxItems(categories.getAllEntities().stream().toList());
        comboBoxPanel.getComboBox().setSelectedItem(selection);
    }

    private void refreshFilter() {
        Category category = (Category) comboBoxPanel.getComboBox().getSelectedItem();
        if (category != null) {
            filter.filterByCategory(category);
        } else {
            filter.removeFilter(Filters.CATEGORY_FILTER);
        }
    }
}
