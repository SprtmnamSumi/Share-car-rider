package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.export.BatchExporterCarRideJSON;
import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sabrina Orálková, 525089
 */
public class ExportDialog extends JDialog implements PropertyChangeListener {

    private final String title = "Export data";
    private final JOptionPane optionPane;

    private String btnString1 = "Export";
    private String btnString2 = "Cancel";

    private File selectedFile;

    private CarRideTableFilter carRideTableFilter;

    public ExportDialog(Frame aFrame, String aWord, CarRideTableFilter carRideTableFilter) {
        super(aFrame, true);
        this.carRideTableFilter = carRideTableFilter;
        setTitle(title);

        String msgString1 = "Select a file";
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
        Object[] array = {msgString1, fileButton};

        Object[] options = {btnString1, btnString2};

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

            if (btnString1.equals(value)) {
                if (selectedFile != null) {
                    // Perform the export operation with the selected file
                    performExport(selectedFile);
                }
            }

            clearAndHide();
        }
    }



    private void performExport(File file) {
        System.out.println("Exporting data to file: " + file.getAbsolutePath());
        //if (carRideTableFilter == null) {
        //    throw new IllegalStateException("CarRideTableFilter not injected");
        //}

        System.out.println("Exporting data to file: " + selectedFile.getAbsolutePath());
        List<CarRide> carRideList = new LinkedList<>();
        carRideList =  carRideTableFilter.getRideCompoundMatcher().getData();

        BatchExporterCarRideJSON batchExporterCarRideJSON = new BatchExporterCarRideJSON();
        batchExporterCarRideJSON.exportData(carRideList, selectedFile.getAbsolutePath());
    }

    public void clearAndHide() {
        setVisible(false);
    }
}