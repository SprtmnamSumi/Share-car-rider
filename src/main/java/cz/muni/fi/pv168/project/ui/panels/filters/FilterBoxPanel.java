package cz.muni.fi.pv168.project.ui.panels.filters;

import cz.muni.fi.pv168.project.business.model.Model;
import cz.muni.fi.pv168.project.ui.filters.Filters;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.ComboBoxPanel;

public class FilterBoxPanel<T extends Model> extends FilterPanel {
    private final ComboBoxPanel<T> comboBoxPanel;
    private final TableModel<T> currencyTableModel;
    private final Filters filterType;
    private final ICarRideTableFilter filter;

    public FilterBoxPanel(ICarRideTableFilter filter, TableModel<T> currencyTableModel, Filters filterType, String name) {
        super();
        this.filter = filter;
        this.filterType = filterType;
        this.comboBoxPanel = new ComboBoxPanel<>(name);
        this.currencyTableModel = currencyTableModel;
        this.add(comboBoxPanel);
        comboBoxPanel.getComboBox().addItemListener((e) -> refreshFilter());
    }

    public T getSelectedItem() {
        return (T) comboBoxPanel.getComboBox().getSelectedItem();
    }

    @Override
    public void reset() {
        comboBoxPanel.getComboBox().setSelectedItem(null);
    }

    public void updateValues() {
        T selection = getSelectedItem();
        comboBoxPanel.setComboBoxItems(currencyTableModel.getAllEntities().stream().toList());
        comboBoxPanel.getComboBox().setSelectedItem(selection);
    }

    private void refreshFilter() {
        if (this.getSelectedItem() != null) {
            filter.filterByEntity(this.getSelectedItem(), filterType);
        } else {
            filter.removeFilter(filterType);
        }
    }
}
