package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.export.BatchExporterCarRideJSON;
import cz.muni.fi.pv168.project.export.BatchExporterCategoryJSON;
import cz.muni.fi.pv168.project.export.BatchExporterCurrencyJSON;
import cz.muni.fi.pv168.project.export.BatchExporterTemplateJSON;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class ExportDialog extends JDialog implements PropertyChangeListener {
    private final static String EXPORT = "Export";
    private final static String CANCEL = "Cancel";
    private final JOptionPane optionPane;
    private final TableModel<Template> templates;
    private final TableModel<Currency> currencies;
    private final TableModel<Category> categories;
    private final ICarRideTableFilter carRideTableFilter;
    private final JButton fileButton = new JButton("Select a file");
    private final JComboBox<String> exportOptionsComboBox = new JComboBox<>(new String[]{"Car Rides", "Currency", "Category", "Template"});
    private File selectedFile;

    ExportDialog(ICarRideTableFilter carRideTableFilter, TableModel<Template> templates, TableModel<Currency> currencies, TableModel<Category> categories) {
        super(Frame.getFrames()[0], "Export data", true);
        this.carRideTableFilter = carRideTableFilter;
        this.templates = templates;
        this.currencies = currencies;
        this.categories = categories;

        exportOptionsComboBox.setSelectedIndex(0); // Default selection

        fileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(ExportDialog.this) == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
            }
        });

        Object[] fileSelection = {"Select a file", fileButton, "Select data to export:", exportOptionsComboBox};
        Object[] options = {EXPORT, CANCEL};

        optionPane = new JOptionPane(fileSelection,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.YES_NO_OPTION,
                null,
                options,
                options[0]);

        setContentPane(optionPane);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                optionPane.setValue(JOptionPane.CLOSED_OPTION);
            }
        });
        optionPane.addPropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();

        if (isVisible()
                && (e.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(prop)
                || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                // ignore reset
                return;
            }

            // Reset the JOptionPane's value
            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

            if (EXPORT.equals(value) && selectedFile != null) {
                performExport((String) Objects.requireNonNull(exportOptionsComboBox.getSelectedItem()));
            }

            clearAndHide();
        }
    }


    private void performExport(String selectedExportOption) {

        switch (selectedExportOption) {
            case "Car Rides" -> {
                List<CarRide> carRideList = carRideTableFilter.getRideCompoundMatcher().getData();
                BatchExporterCarRideJSON batchExporterCarRideJSON = new BatchExporterCarRideJSON();
                batchExporterCarRideJSON.exportData(carRideList, selectedFile.getAbsolutePath());
            }
            case "Currency" -> {
                List<Currency> currencyList = currencies.getAll();
                BatchExporterCurrencyJSON batchExporterCurrencyJSON = new BatchExporterCurrencyJSON();
                batchExporterCurrencyJSON.exportData(currencyList, selectedFile.getAbsolutePath());
            }
            case "Category" -> {
                List<Category> categoryList = categories.getAll();
                BatchExporterCategoryJSON batchExporterCategoryJSON = new BatchExporterCategoryJSON();
                batchExporterCategoryJSON.exportData(categoryList, selectedFile.getAbsolutePath());
            }
            case "Template" -> {
                List<Template> templateList = templates.getAll();
                BatchExporterTemplateJSON batchExporterTemplateJSON = new BatchExporterTemplateJSON();
                batchExporterTemplateJSON.exportData(templateList, selectedFile.getAbsolutePath());
            }
            default -> throw new IllegalStateException("You shouldn't be here, how did you even get here?");
        }
    }

    private void clearAndHide() {
        setVisible(false);
    }
}