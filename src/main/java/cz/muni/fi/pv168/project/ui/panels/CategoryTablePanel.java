package cz.muni.fi.pv168.project.ui.panels;

//import cz.muni.fi.pv168.project.entities.old.Gender;

import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel with employee records in a table.
 */
public class CategoryTablePanel extends JPanel {

    private final JTable table;
    private final Consumer<Integer> onSelectionChange;

    public CategoryTablePanel(CategoryTableModel carRideTableModel, Consumer<Integer> onSelectionChange) {
        setLayout(new BorderLayout());
        table = setUpTable(carRideTableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(new JLabel("Filtered distance"), BorderLayout.PAGE_END);

        this.onSelectionChange = onSelectionChange;
    }

    public JTable getTable() {
        return table;
    }

    private JTable setUpTable(CategoryTableModel carRideTableModel) {
        var table = new JTable(carRideTableModel);

        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
//        var genderComboBox = new JComboBox<>(Gender.values());
//        table.setDefaultEditor(Gender.class, new DefaultCellEditor(genderComboBox));
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
