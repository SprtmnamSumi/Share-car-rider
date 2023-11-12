package cz.muni.fi.pv168.project.ui.dialog;

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
public class ImportDialog extends JDialog
        implements PropertyChangeListener {

    private final String title = "Import data";
    private final JOptionPane optionPane;

    private String btnString1 = "Enter";
    private String btnString2 = "Cancel";

    public ImportDialog(Frame aFrame, String aWord) {
        super(aFrame, true);
        setTitle(title);

        String msgString1 = "Select a file";
        JButton fileButton = new JButton("Select a file");
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Use the Desktop class to open the system's file explorer
                    Desktop.getDesktop().open(new File("."));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Object[] array = {msgString1, fileButton};

        //Create an array specifying the number of dialog buttons
        //and their text.
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

    /** This method reacts to state changes in the option pane. */
    public void propertyChange(PropertyChangeEvent e) {
        clearAndHide();
    }

    public void clearAndHide() {
        setVisible(false);
    }
}