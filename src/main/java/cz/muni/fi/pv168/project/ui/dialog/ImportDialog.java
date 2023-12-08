package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.data.ImportInitializer;
import cz.muni.fi.pv168.project.export.BatchImporterCarRideJSON;
import cz.muni.fi.pv168.project.export.BatchImporterCategoryJSON;
import cz.muni.fi.pv168.project.export.BatchImporterCurrencyJSON;
import cz.muni.fi.pv168.project.export.BatchImporterTemplateJSON;
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
import java.util.List;
import javax.swing.*;
import javax.swing.JComboBox;

public class ImportDialog extends JDialog implements PropertyChangeListener {

    private final String title = "Import data";
    private final JOptionPane optionPane;

    private final String btnString1 = "Import";
    private final String btnString2 = "Cancel";
    private final String btnString3 = "Overwrite";


    private final TableModel<Template> templates;
    private final TableModel<Currency> currencies;
    private final TableModel<Category> categories;
    private final ICarRideTableFilter carRideTableFilter;
    private final JComboBox<String> importOptionsComboBox;

    private File selectedFile;
    private String selectedImportOption;
    private ImportInitializer importInitializator;

    public ImportDialog(Frame aFrame, String aWord, TableModel<Template> templates, TableModel<Currency> currencies, TableModel<Category> categories, ICarRideTableFilter carRideTableFilter, ImportInitializer importInitializator) {
        super(aFrame, true);
        this.templates = templates;
        this.currencies = currencies;
        this.categories = categories;
        this.carRideTableFilter = carRideTableFilter;
        this.importInitializator = importInitializator;
        setTitle(title);

        // Create a combo box for export options
        importOptionsComboBox = new JComboBox<>(new String[]{"Car Rides", "Currency", "Category", "Template"});
        importOptionsComboBox.setSelectedIndex(0); // Default selection

        String msgString1 = "Select a file";
        JButton fileButton = new JButton("Select a file");
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(ImportDialog.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                }
            }
        });

        Object[] array = {msgString1, fileButton, "Select data to export:", importOptionsComboBox};

        Object[] options = {btnString1, btnString2, btnString3};

        optionPane = new JOptionPane(array,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.YES_NO_CANCEL_OPTION,
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
                return;
            }

            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);


            if (btnString1.equals(value)) {
                if (selectedFile != null) {
                    selectedImportOption = (String) importOptionsComboBox.getSelectedItem();
                    performImport(selectedFile);
                }
            } else if (btnString3.equals(value)) {
                if (selectedFile != null) {
                    selectedImportOption = (String) importOptionsComboBox.getSelectedItem();
                    performOverwrite(selectedFile);
                }
            }

            clearAndHide();
        }
    }

    private void performImport(File file) {
        System.out.println("Importing data from file: " + file.getAbsolutePath());
        switch (selectedImportOption) {
            case "Car Rides":
                BatchImporterCarRideJSON batchImporterCarRideJSON = new BatchImporterCarRideJSON();
                List<CarRide> carRideList =  batchImporterCarRideJSON.importData(file.toPath());
                importInitializator.initializeCarRide(carRideList);
                break;
            case "Currency":
                BatchImporterCurrencyJSON batchImporterCurrencyJSON = new BatchImporterCurrencyJSON();
                List<Currency>currencyList = batchImporterCurrencyJSON.importData(file.toPath());
                importInitializator.initializeCurrency(currencyList);
                break;
            case "Category":
                BatchImporterCategoryJSON batchImporterCategoryJSON = new BatchImporterCategoryJSON();
                List<Category> categoryList = batchImporterCategoryJSON.importData(file.toPath());
                importInitializator.initializeCategory(categoryList);
                break;
            case "Template":
                BatchImporterTemplateJSON batchImporterTemplateJSON = new BatchImporterTemplateJSON();
                List<Template> templateList =  batchImporterTemplateJSON.importData(file.toPath());
                importInitializator.initializeTemplate(templateList);
                break;
            default:
                throw new IllegalStateException("You shouldn't be here, how did you even get here?");
        }
    }

    private void performOverwrite(File file) {
        System.out.println("Performing overwrite operation");

        switch (selectedImportOption) {
            case "Car Rides":
                importInitializator.redoCarRide();
                break;
            case "Currency":
                importInitializator.redoCurrency();
                break;
            case "Category":
                importInitializator.redoCategory();
                break;
            case "Template":
                importInitializator.redoTemplate();
                break;
            default:
                throw new IllegalStateException("You shouldn't be here, how did you even get here?");
        }
        performImport(file);
    }

    public void clearAndHide() {
        setVisible(false);
    }
}