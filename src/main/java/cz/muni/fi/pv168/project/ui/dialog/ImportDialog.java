package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Entity;
import cz.muni.fi.pv168.project.storage.InMemoryRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImportDialog extends JDialog implements PropertyChangeListener {

    private final String title = "Import data";
    private final JOptionPane optionPane;

    private String btnString1 = "Import";
    private String btnString2 = "Cancel";
    private String btnString3 = "Overwrite";

    private File selectedFile;

    public ImportDialog(Frame aFrame, String aWord) {
        super(aFrame, true);
        setTitle(title);

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

        Object[] array = {msgString1, fileButton};

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
                    performImport(selectedFile);
                }
            } else if (btnString3.equals(value)) {
                performOverwrite();
            }

            clearAndHide();
        }
    }

    private void performImport(File file) {
        System.out.println("Importing data from file: " + file.getAbsolutePath());
    }

    private void performOverwrite() {
        System.out.println("Performing overwrite operation");

    }

    public void clearAndHide() {
        setVisible(false);
    }
}