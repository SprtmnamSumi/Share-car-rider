package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.data.ImportInitializer;
import cz.muni.fi.pv168.project.export.BatchImporterCarRideJSON;
import cz.muni.fi.pv168.project.export.BatchImporterCategoryJSON;
import cz.muni.fi.pv168.project.export.BatchImporterCurrencyJSON;
import cz.muni.fi.pv168.project.export.BatchImporterTemplateJSON;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.tinylog.Logger;

public class ImportDialog extends JDialog implements PropertyChangeListener {

    private final JOptionPane optionPane;
    private final String importName = "Import";
    private final String overwriteName = "Overwrite";
    private final JComboBox<String> importOptionsComboBox;
    private final ImportInitializer importInitializer;
    private File selectedFile;
    private String selectedImportOption;

    public ImportDialog(ImportInitializer importInitializer) {
        super(Frame.getFrames()[0], "Import data", true);
        this.importInitializer = importInitializer;


        // Create a combo box for export options
        importOptionsComboBox = new JComboBox<>(new String[]{"Car Rides", "Currency", "Category", "Template"});
        importOptionsComboBox.setSelectedIndex(0); // Default selection

        Object[] array = getObjects();

        String cancelName = "Cancel";
        Object[] options = {importName, cancelName, overwriteName};

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

    private Object[] getObjects() {
        String msgString1 = "Select a file";
        JButton fileButton = new JButton("Select a file");
        fileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(ImportDialog.this);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
            }
        });

        Object[] array = {msgString1, fileButton, "Select data to export:", importOptionsComboBox};
        return array;
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


            if (importName.equals(value)) {
                if (selectedFile != null) {
                    selectedImportOption = (String) importOptionsComboBox.getSelectedItem();
                    performImport(selectedFile);
                }
            } else if (overwriteName.equals(value)) {
                if (selectedFile != null) {
                    selectedImportOption = (String) importOptionsComboBox.getSelectedItem();
                    performOverwrite(selectedFile);
                }
            }

            clearAndHide();
        }
    }

    private void performImport(File file) {
        Initialize(file, false);
    }

    private void performOverwrite(File file) {
        Logger.info("Performing overwrite before import from file: " + file.getAbsolutePath());
        Initialize(file, true);
    }

    private void Initialize(File file, boolean overwrite) {
        Logger.info("Importing data from file: " + file.getAbsolutePath());
        switch (selectedImportOption) {
            case "Car Rides":
                BatchImporterCarRideJSON batchImporterCarRideJSON = new BatchImporterCarRideJSON();
                batchImporterCarRideJSON.importData(file.toPath(), importInitializer, overwrite);
                break;
            case "Currency":
                BatchImporterCurrencyJSON batchImporterCurrencyJSON = new BatchImporterCurrencyJSON();
                batchImporterCurrencyJSON.importData(file.toPath(), importInitializer, overwrite);
                break;
            case "Category":
                BatchImporterCategoryJSON batchImporterCategoryJSON = new BatchImporterCategoryJSON();
                batchImporterCategoryJSON.importData(file.toPath(), importInitializer, overwrite);
                break;
            case "Template":
                BatchImporterTemplateJSON batchImporterTemplateJSON = new BatchImporterTemplateJSON();
                batchImporterTemplateJSON.importData(file.toPath(), importInitializer, overwrite);
                break;
            default:
                Logger.error("Selected unsupported import action.");
                throw new IllegalStateException("You shouldn't be here, how did you even get here?");
        }
    }

    public void clearAndHide() {
        setVisible(false);
    }
}