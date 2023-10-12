package cz.muni.fi.pv168.project.ui.panels.OLD;

import cz.muni.fi.pv168.project.entities.old.Department;
import cz.muni.fi.pv168.project.entities.old.Gender;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.model.OLDEmployeeTableModel;
import cz.muni.fi.pv168.project.ui.model.old.DepartmentListModel;
import cz.muni.fi.pv168.project.ui.panels.CarRide.CarRideTablePanel;
import cz.muni.fi.pv168.project.ui.renderers.LocalDateRenderer;
import cz.muni.fi.pv168.project.ui.renderers.OLD.DepartmentRenderer;
import cz.muni.fi.pv168.project.ui.renderers.OLD.GenderRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.time.LocalDate;
import java.util.function.Consumer;

/**
 * Panel with employee records in a table.
 */
public class EmployeeTablePanel extends JPanel {

    private final JTable table;
    private final Consumer<Integer> onSelectionChange;

    public EmployeeTablePanel(OLDEmployeeTableModel OLDEmployeeTableModel, DepartmentListModel departmentListModel, Consumer<Integer> onSelectionChange) {
        setLayout(new BorderLayout());
        table = setUpTable(OLDEmployeeTableModel, departmentListModel);
        add(new CarRideTablePanel.FilterBar(), BorderLayout.PAGE_START);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(new JLabel("Filtered distance"), BorderLayout.PAGE_END);

        this.onSelectionChange = onSelectionChange;
    }

    public JTable getTable() {
        return table;
    }

    private JTable setUpTable(OLDEmployeeTableModel OLDEmployeeTableModel, DepartmentListModel departmentListModel) {
        var table = new JTable(OLDEmployeeTableModel);

        var departmentRenderer = new DepartmentRenderer();

        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        var genderComboBox = new JComboBox<>(Gender.values());
        table.setDefaultEditor(Gender.class, new DefaultCellEditor(genderComboBox));

        var departmentComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(departmentListModel));
        departmentComboBox.setRenderer(departmentRenderer);
        table.setDefaultEditor(Department.class, new DefaultCellEditor(departmentComboBox));

        table.setDefaultRenderer(Gender.class, new GenderRenderer());
        table.setDefaultRenderer(Department.class, departmentRenderer);
        table.setDefaultRenderer(LocalDate.class, new LocalDateRenderer());

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
