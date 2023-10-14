package cz.muni.fi.pv168.project.ui.panels.CarRide;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.entities.Currency;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryListModel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.ComboBoxPanel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.SpinnerDatePanel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TextFieldPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
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
        add(new FilterBar(), BorderLayout.PAGE_START);

        add(new JScrollPane(table), BorderLayout.CENTER);
        JLabel filteredDistance = new JLabel("Filtered distance");
        filteredDistance.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        add(filteredDistance, BorderLayout.PAGE_END);

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

    public static class FilterBar extends JPanel {
        private final List<String> currencyModel = Arrays.stream(Currency.values()).map(Currency::name).toList();
        private final List<String> categories;

        public FilterBar() {
            super(new FlowLayout(FlowLayout.LEFT));
            TestDataGenerator testDataGenerator = new TestDataGenerator();
            categories = testDataGenerator.createTestCategories(10).stream().map(Category::getName).toList();

            //        // Set look
            //        this.setMaximumSize(dimension);

            TextFieldPanel numberOfPassengersPanel = new TextFieldPanel("Number Of Passengers");
            this.add(numberOfPassengersPanel);

            SpinnerDatePanel dateFromPanel = new SpinnerDatePanel("Date From");
            this.add(dateFromPanel);

            SpinnerDatePanel dateToPanel = new SpinnerDatePanel("Date to");
            this.add(dateToPanel);

            ComboBoxPanel categoryPanel = new ComboBoxPanel("Category");
            categoryPanel.setComboBoxItems(categories);
            this.add(categoryPanel);

            ComboBoxPanel currencyPanel = new ComboBoxPanel("Currency");
            currencyPanel.setComboBoxItems(currencyModel);
            this.add(currencyPanel);

            TextFieldPanel distanceFromPanel = new TextFieldPanel("Distance from");
            this.add(distanceFromPanel);

            TextFieldPanel distanceToPanel = new TextFieldPanel("Distance to");
            this.add(distanceToPanel);

            JButton filterButton = new JButton("Reset Filter");
            this.add(filterButton);
        }
    }
}
