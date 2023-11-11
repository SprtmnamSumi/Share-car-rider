package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.ui.dialog.ImportDialog;

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

/**
 * @author Sabrina Orálková, 525089
 */
public class ExportDialog extends JDialog
        implements PropertyChangeListener {

    private final String title = "Export data";
    private final JOptionPane optionPane;

    private String btnString1 = "Export";
    private String btnString2 = "Cancel";

    private File selectedFile;

    public ExportDialog(Frame aFrame, String aWord) {
        super(aFrame, true);
        setTitle(title);

        String msgString1 = "Select a file";
        JButton fileButton = new JButton("Select a file");
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(ExportDialog.this);

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
        clearAndHide();
    }

    public void clearAndHide() {
        setVisible(false);
    }
}