package cz.muni.fi.pv168.project.ui.dialog;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

abstract class IODialog extends JDialog {
    protected final JOptionPane optionPane;
    protected final JComboBox<String> entityOptionsComboBox = new JComboBox<>(new String[]{"Car Rides", "Currency", "Category", "Template"});
    private final JButton fileButton = new JButton("Select a file");
    private final JButton actionButton;
    private File selectedFile;

    IODialog(String title, boolean showSelectData, String... actions) {
        super(Frame.getFrames()[0], title, true);
        entityOptionsComboBox.setSelectedIndex(0); // Default selection

        actionButton = new JButton(actions[0]);
        actionButton.setEnabled(false);

        Object[] fileSelection = showSelectData
                ? new Object[]{"Select a file:", fileButton, "Select data:", entityOptionsComboBox}
                : new Object[]{"Select a file:", fileButton};

        optionPane = new JOptionPane(fileSelection,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.YES_NO_OPTION,
                null,
                actions,
                actions[0]);

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
        this.setContentPane(optionPane);
        this.setMinimumSize(new Dimension(this.getContentPane().getPreferredSize().width + 25, this.getContentPane().getPreferredSize().height));
        this.setResizable(false);

    }

    void forceSelectEntity(String entity) {
        entityOptionsComboBox.removeAllItems();
        entityOptionsComboBox.addItem(entity);
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
