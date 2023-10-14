package cz.muni.fi.pv168.project.ui.panels.CarRide;

import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryListModel;
import cz.muni.fi.pv168.project.ui.panels.TemplateablePanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel with employee records in a table.
 */
public class CarRideTablePanel extends JPanel {

    private final JTable table;
    private final Consumer<Integer> onSelectionChange;

    public CarRideTablePanel(CarRideTableModel carRideTableModel, CategoryListModel categoryListModel, Consumer<Integer> onSelectionChange) {
        setLayout(new BorderLayout());
        table = setUpTable(carRideTableModel, categoryListModel);
        CarRideFilterBar filterBar = new CarRideFilterBar();
        CarRideStatisticsBar statsPanel = new CarRideStatisticsBar(carRideTableModel);
        table.getModel().addTableModelListener(e->
        {
            statsPanel.updateFilteredStats();
            statsPanel.updateTotalStats();
        });

        add(filterBar, BorderLayout.PAGE_START);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(statsPanel, BorderLayout.PAGE_END);

        this.onSelectionChange = onSelectionChange;
    }

    public JTable getTable() {
        return table;
    }

    private JTable setUpTable(CarRideTableModel carRideTableModel, CategoryListModel departmentListModel) {
        var table = new JTable(carRideTableModel);

        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
//        var genderComboBox = new JComboBox<>(Gender.values());
//        table.setDefaultEditor(Gender.class, new DefaultCellEditor(genderComboBox));
//        var departmentComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(departmentListModel));
//        table.setDefaultEditor(Department.class, new DefaultCellEditor(departmentComboBox));
//
//        table.setDefaultRenderer(Gender.class, new GenderRenderer());

        return table;
    }

    private void rowSelectionChanged(ListSelectionEvent listSelectionEvent) {
        var selectionModel = (ListSelectionModel) listSelectionEvent.getSource();
        var count = selectionModel.getSelectedItemsCount();
        if (onSelectionChange != null) {
            onSelectionChange.accept(count);
        }
    }
}
