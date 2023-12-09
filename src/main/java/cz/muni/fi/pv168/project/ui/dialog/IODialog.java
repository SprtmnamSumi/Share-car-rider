package cz.muni.fi.pv168.project.ui.dialog;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.Objects;

abstract class IODialog extends JDialog {
    private final JButton fileButton = new JButton("Select a file");
    private final JButton actionButton;
    private File selectedFile;
    protected final JOptionPane optionPane;
    protected final JComboBox<String> entityOptionsComboBox = new JComboBox<>(new String[]{"Car Rides", "Currency", "Category", "Template"});

    IODialog(String... actions) {
        super(Frame.getFrames()[0], actions[0], true);
        this.setResizable(false);
        entityOptionsComboBox.setSelectedIndex(0); // Default selection

        actionButton = new JButton(actions[0]);
        actionButton.setEnabled(false);
        Object[] fileSelection = {"Select a file:", fileButton, "Select data:", entityOptionsComboBox};
        optionPane = new JOptionPane(fileSelection,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.YES_NO_OPTION,
                null,
                actions,
                actions[0]);

        this.setContentPane(optionPane);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                optionPane.setValue(JOptionPane.CLOSED_OPTION);
            }
        });

        fileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                fileButton.setText(selectedFile.getName());
                actionButton.setEnabled(true);
            }
        });
        optionPane.addPropertyChangeListener(this::propertyChange);

    }
    String getSelectedEntity() {
        return (String) entityOptionsComboBox.getSelectedItem();
    }
    File getSelectedFile() {
        return selectedFile;
    }

    private void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        if (isVisible() && (e.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(prop) || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            setVisible(false);
        }
    }
}
