package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.data.IImportInitializer;
import cz.muni.fi.pv168.project.data.ImportInitializer;
import cz.muni.fi.pv168.project.export.BatchImporterCarRideJSON;
import cz.muni.fi.pv168.project.export.BatchImporterCategoryJSON;
import cz.muni.fi.pv168.project.export.BatchImporterCurrencyJSON;
import cz.muni.fi.pv168.project.export.BatchImporterTemplateJSON;
import cz.muni.fi.pv168.project.ui.workers.WorkerProvider;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;
import javax.swing.JOptionPane;
import org.tinylog.Logger;

public class ImportDialog extends IODialog {
    private final static String IMPORT = "Import";
    private final static String CANCEL = "Cancel";
    private final static String OVERWRITE = "Overwrite";
    private final static String COMPLEMENT = "Try-Import";


    private final WorkerProvider workerProvider;
    private final ImportInitializer importInitializer;

    ImportDialog(WorkerProvider workerProvider, ImportInitializer importInitializer) {
        super("Import data", false, CANCEL, OVERWRITE, COMPLEMENT, IMPORT);
        this.importInitializer = importInitializer;
        this.workerProvider = workerProvider;

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE); // Reset the JOptionPane's value
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                if (getSelectedFile() != null) {
                    if (IMPORT.equals(optionPane.getValue())) {
                        performImport(getSelectedFile());
                    }
                    if (OVERWRITE.equals(optionPane.getValue())) {
                        performOverwrite(getSelectedFile());
                    }
                    if (COMPLEMENT.equals(optionPane.getValue())) {
                        performComplementImport(getSelectedFile());
                    }
                }
            }
        });
    }

    private EntityType getEntityType(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            if (line == null) {
                Logger.error("File is empty");
                JOptionPane.showMessageDialog(this, "File is empty");
                return EntityType.UNKNOWN;
            }

            if (line.contains("{\"carrides\": [")) {
                return EntityType.CAR_RIDE;
            }

            if (line.contains("{\"categories\": [")) {
                return EntityType.CATEGORY;
            }

            if (line.contains("{\"currencies\": [")) {
                return EntityType.CURRENCY;
            }

            if (line.contains("{\"templates\": [")) {
                return EntityType.TEMPLATE;
            }

        } catch (IOException e) {
            Logger.error("Error while reading file");
            JOptionPane.showMessageDialog(this, "Error while reading file");
            throw new IllegalStateException("Error while reading file", e);
        }
        return EntityType.UNKNOWN;
    }

    private void Initialize(File file, IImportInitializer.MODE mode) {
        Logger.info("Importing data from file: " + file.getAbsolutePath());
        EntityType entityType = getEntityType(file);
        Function<Void, Boolean> importFunction = switch (entityType) {
            case CAR_RIDE -> (x) -> new BatchImporterCarRideJSON().importData(file.toPath(), importInitializer, mode);
            case CATEGORY -> (x) -> new BatchImporterCategoryJSON().importData(file.toPath(), importInitializer, mode);
            case CURRENCY -> (x) -> new BatchImporterCurrencyJSON().importData(file.toPath(), importInitializer, mode);
            case TEMPLATE -> (x) -> new BatchImporterTemplateJSON().importData(file.toPath(), importInitializer, mode);
            case UNKNOWN -> (x) -> {
                Logger.error("Unknown entity type");
                JOptionPane.showMessageDialog(this, "Unknown entity type");
                return false;
            };
            default -> {
                Logger.error("Selected unsupported import action.");
                throw new IllegalStateException("You shouldn't be here, how did you even get here?");
            }
        };
        boolean success = workerProvider.submitTask(importFunction,
                () -> JOptionPane.showMessageDialog(this, "Import has NOT successfully finished, incorrect file format or incorrect import option"),
                "Import");
        if (!success) {
            Logger.info("Import did not start, because another IO action is in progress");
            JOptionPane.showMessageDialog(this, "Import did not start, because another blocking IO is in progress");
        }
    }

    private void performImport(File file) {
        Initialize(file, IImportInitializer.MODE.ADD);
    }

    private void performComplementImport(File file) {
        Initialize(file, IImportInitializer.MODE.COMPLEMENT);
    }

    private void performOverwrite(File file) {
        Logger.info("Performing overwrite before import from file: " + file.getAbsolutePath());
        Initialize(file, IImportInitializer.MODE.OVERWRITE);
    }

    private enum EntityType {
        CAR_RIDE,
        CATEGORY,
        CURRENCY,
        TEMPLATE,
        UNKNOWN
    }
}