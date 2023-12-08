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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class ExportDialog extends JDialog implements PropertyChangeListener {

    private final String title = "Export data";
    private final JOptionPane optionPane;
    private final TableModel<Template> templates;
    private final TableModel<Currency> currencies;
    private final TableModel<Category> categories;
    private final String btnExportTitle = "Export";
    private final String btnCancelTitle = "Cancel";
    private final ICarRideTableFilter carRideTableFilter;
    private final JComboBox<String> exportOptionsComboBox;
    private File selectedFile;
    private String selectedExportOption;

    public ExportDialog(Frame frame, ICarRideTableFilter carRideTableFilter, TableModel<Template> templates, TableModel<Currency> currencies, TableModel<Category> categories) {
        super(frame, true);
        this.carRideTableFilter = carRideTableFilter;
        this.templates = templates;
        this.currencies = currencies;
        this.categories = categories;
        setTitle(title);


        // Create a combo box for export options
        exportOptionsComboBox = new JComboBox<>(new String[]{"Car Rides", "CurrencyEntity", "Category", "Template"});
        exportOptionsComboBox.setSelectedIndex(0); // Default selection

        JButton fileButton = new JButton("Select a file");
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(ExportDialog.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                }
            }
        });

        Object[] array = {"Select a file", fileButton, "Select data to export:", exportOptionsComboBox};

        Object[] options = {btnExportTitle, btnCancelTitle};

        optionPane = new JOptionPane(array,
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

            if (btnExportTitle.equals(value)) {
                if (selectedFile != null) {

                    selectedExportOption = (String) exportOptionsComboBox.getSelectedItem();

                    // TODO if (selectedExportOption == null || selectedExportOption.isEmpty()) {
                    // TODO JOptionPane.showMessageDialog(this, "Please select an export option.", "Warning", JOptionPane.WARNING_MESSAGE);
                    // TODO return;
                    // TODO}
                    performExport(selectedFile);
                }
            }

            clearAndHide();
        }
    }


    private void performExport(File file) {

        switch (selectedExportOption) {
            case "Car Rides":
                List<CarRide> carRideList = new LinkedList<>();
                carRideList = carRideTableFilter.getRideCompoundMatcher().getData();

                BatchExporterCarRideJSON batchExporterCarRideJSON = new BatchExporterCarRideJSON();
                batchExporterCarRideJSON.exportData(carRideList, selectedFile.getAbsolutePath());
                break;
            case "CurrencyEntity":
                List<Currency> currencyList = new LinkedList<>();
                currencyList = currencies.getAll();

                BatchExporterCurrencyJSON batchExporterCurrencyJSON = new BatchExporterCurrencyJSON();
                batchExporterCurrencyJSON.exportData(currencyList, selectedFile.getAbsolutePath());
                break;
            case "Category":
                List<Category> categoryList = new LinkedList<>();
                categoryList = categories.getAll();

                BatchExporterCategoryJSON batchExporterCategoryJSON = new BatchExporterCategoryJSON();
                batchExporterCategoryJSON.exportData(categoryList, selectedFile.getAbsolutePath());
                break;
            case "Template":
                List<Template> templateList = new LinkedList<>();
                templateList = templates.getAll();

                BatchExporterTemplateJSON batchExporterTemplateJSON = new BatchExporterTemplateJSON();
                batchExporterTemplateJSON.exportData(templateList, selectedFile.getAbsolutePath());
                break;
            default:
                throw new IllegalStateException("You shouldn't be here, how did you even get here?");
        }
    }

    public void clearAndHide() {
        setVisible(false);
    }
}