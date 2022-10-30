package cz.muni.fi.pv168.employees.ui.panels;

import cz.muni.fi.pv168.employees.model.Department;
import cz.muni.fi.pv168.employees.model.Gender;
import cz.muni.fi.pv168.employees.ui.model.ComboBoxModelAdapter;
import cz.muni.fi.pv168.employees.ui.model.DepartmentListModel;
import cz.muni.fi.pv168.employees.ui.model.EmployeeTableModel;
import cz.muni.fi.pv168.employees.ui.renderers.GenderRenderer;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import java.awt.BorderLayout;
import java.util.function.Consumer;

/**
 * Panel with employee records in a table.
 */
public class EmployeeTablePanel extends JPanel {

    private final JTable table;
    private final Consumer<Integer> onSelectionChange;

    public EmployeeTablePanel(EmployeeTableModel employeeTableModel, DepartmentListModel departmentListModel, Consumer<Integer> onSelectionChange) {
        setLayout(new BorderLayout());
        table = setUpTable(employeeTableModel, departmentListModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        this.onSelectionChange = onSelectionChange;
    }

    public JTable getTable() {
        return table;
    }

    private JTable setUpTable(EmployeeTableModel employeeTableModel, DepartmentListModel departmentListModel) {
        var table = new JTable(employeeTableModel);

        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        var genderComboBox = new JComboBox<>(Gender.values());
        table.setDefaultEditor(Gender.class, new DefaultCellEditor(genderComboBox));
        var departmentComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(departmentListModel));
        table.setDefaultEditor(Department.class, new DefaultCellEditor(departmentComboBox));

        table.setDefaultRenderer(Gender.class, new GenderRenderer());

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
