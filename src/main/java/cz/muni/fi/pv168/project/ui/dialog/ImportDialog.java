package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.data.IImportInitializer;
import cz.muni.fi.pv168.project.data.ImportInitializer;
import cz.muni.fi.pv168.project.export.BatchImporterCarRideJSON;
import cz.muni.fi.pv168.project.export.BatchImporterCategoryJSON;
import cz.muni.fi.pv168.project.export.BatchImporterCurrencyJSON;
import cz.muni.fi.pv168.project.export.BatchImporterTemplateJSON;
import cz.muni.fi.pv168.project.ui.workers.WorkerProvider;
import org.tinylog.Logger;

import javax.swing.JOptionPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.function.Function;

public class ImportDialog extends IODialog {
    private final static String IMPORT = "Import";
    private final static String CANCEL = "Cancel";
    private final static String OVERWRITE = "Overwrite";
    private final WorkerProvider workerProvider;
    private final ImportInitializer importInitializer;

    ImportDialog(WorkerProvider workerProvider, ImportInitializer importInitializer) {
        super("Import data", IMPORT, CANCEL, OVERWRITE);
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
                        performImport(getSelectedEntity(), getSelectedFile());
                    }
                    if (OVERWRITE.equals(optionPane.getValue())) {
                        performOverwrite(getSelectedEntity(), getSelectedFile());
                    }
                }
            }
        });
    }

    private void Initialize(String importOption, File file, boolean overwrite) {
        Logger.info("Importing data from file: " + file.getAbsolutePath());
        IImportInitializer.MODE mode = overwrite ? IImportInitializer.MODE.OVERWRITE : IImportInitializer.MODE.ADD;
        Function<Void, Boolean> importFunction = switch (importOption) {
            case "Car Rides" ->
                    (x) -> new BatchImporterCarRideJSON().importData(file.toPath(), importInitializer, mode);
            case "Currency" ->
                    (x) -> new BatchImporterCurrencyJSON().importData(file.toPath(), importInitializer, mode);
            case "Category" ->
                    (x) -> new BatchImporterCategoryJSON().importData(file.toPath(), importInitializer, mode);
            case "Template" ->
                    (x) -> new BatchImporterTemplateJSON().importData(file.toPath(), importInitializer, mode);
            default -> {
                Logger.error("Selected unsupported import action.");
                throw new IllegalStateException("You shouldn't be here, how did you even get here?");
            }
        };
        boolean success = workerProvider.submitTask(importFunction,
                () -> JOptionPane.showMessageDialog(this, "Import has NOT successfully finished."),
                "Import");
        if(!success){
            Logger.info("Import did not start, because another IO action is in progress");
            JOptionPane.showMessageDialog(this, "Import did not start, because another blocking IO is in progress");
        }
    }

    private void performImport(String importOption, File file) {
        Initialize(importOption, file, false);
    }

    private void performOverwrite(String importOption, File file) {
        Logger.info("Performing overwrite before import from file: " + file.getAbsolutePath());
        Initialize(importOption, file, true);
    }
}