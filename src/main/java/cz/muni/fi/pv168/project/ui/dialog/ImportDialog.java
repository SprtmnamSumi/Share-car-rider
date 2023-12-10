package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.data.ImportInitializer;
import cz.muni.fi.pv168.project.export.BatchImporterCarRideJSON;
import cz.muni.fi.pv168.project.export.BatchImporterCategoryJSON;
import cz.muni.fi.pv168.project.export.BatchImporterCurrencyJSON;
import cz.muni.fi.pv168.project.export.BatchImporterTemplateJSON;
import org.tinylog.Logger;

import javax.swing.JOptionPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

public class ImportDialog extends IODialog{
    private final static String IMPORT = "Import";
    private final static String CANCEL = "Cancel";
    private final static String OVERWRITE = "Overwrite";
    private final ImportInitializer importInitializer;

    ImportDialog(ImportInitializer importInitializer) {
        super(IMPORT, CANCEL, OVERWRITE);
        this.importInitializer = importInitializer;

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE); // Reset the JOptionPane's value
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                if (getSelectedFile() != null) {
                    if(IMPORT.equals(optionPane.getValue())){
                        performImport(getSelectedEntity(), getSelectedFile());
                    }
                    if(OVERWRITE.equals(optionPane.getValue())){
                        performOverwrite(getSelectedEntity(), getSelectedFile());
                    }
                }
            }
        });
    }

    private void performImport(String importOption, File file) {
        Logger.info("Importing data from file: " + file.getAbsolutePath());
        switch (importOption) {
            case "Car Rides" -> {
                importInitializer.initializeCarRide(
                        new BatchImporterCarRideJSON().importData(file.toPath()));
            }
            case "Currency" -> {
                importInitializer.initializeCurrency(
                        new BatchImporterCurrencyJSON().importData(file.toPath()));
            }
            case "Category" -> {
                importInitializer.initializeCategory(
                        new BatchImporterCategoryJSON().importData(file.toPath()));
            }
            case "Template" -> {
                importInitializer.initializeTemplate(
                        new BatchImporterTemplateJSON().importData(file.toPath()));
            }
            default -> {
                Logger.error("Selected unsupported import action.");
                throw new IllegalStateException("You shouldn't be here, how did you even get here?");
            }
        }
    }

    private void performOverwrite(String importOption, File file) {
        Logger.info("Performing overwrite before import from file: " + file.getAbsolutePath());
        switch (importOption) {
            case "Car Rides" -> importInitializer.redoCarRide();
            case "Currency" -> importInitializer.redoCurrency();
            case "Category" -> importInitializer.redoCategory();
            case "Template" -> importInitializer.redoTemplate();
            default -> throw new IllegalStateException("You shouldn't be here, how did you even get here?");
        }
        performImport(importOption, file);
    }
}