package cz.muni.fi.pv168.project.ui.dialog;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Optional;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.WEST;
import static javax.swing.JOptionPane.*;

abstract class EntityDialog<E> {

    private boolean pressedOK = false;

    private JDialog dialog;

    private final JButton okButton = new JButton("Add");
    private final JButton cancelButton = new JButton("Cancel");

    protected final JPanel panel = new JPanel();

    EntityDialog() {
        panel.setLayout(new MigLayout("wrap 2"));
        okButton.setEnabled(false);
        okButton.addActionListener((a)->{dialog.setVisible(false);
            pressedOK = true;});
        cancelButton.addActionListener((a)->dialog.setVisible(false));
    }

    void add(String labelText, JComponent component) {
        var label = new JLabel(labelText);
        panel.add(label);
        panel.add(component, "wmin 250lp, grow");
    }

    void toggleOk(boolean state){
        okButton.setEnabled(state);
    }

    abstract E getEntity();

    public Optional<E> show(JComponent parentComponent, String title) {
        addDialogControls();
        dialog = new JDialog(Frame.getFrames()[0], title, Dialog.ModalityType.APPLICATION_MODAL);
        panel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        dialog.setResizable(false);
        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
        return pressedOK ? Optional.of(getEntity()) : Optional.empty();
    }

    private void addDialogControls(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(this.panel.getSize().width,5));
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(okButton);
        panel.add(cancelButton);
        add("",panel);
    }
}
