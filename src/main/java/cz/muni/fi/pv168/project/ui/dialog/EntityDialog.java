package cz.muni.fi.pv168.project.ui.dialog;

import net.miginfocom.swing.MigLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.Optional;

public abstract class EntityDialog<E> {

    protected final JPanel panel = new JPanel();
    private final JButton okButton = new JButton();
    private final JButton cancelButton = new JButton("Cancel");
    private boolean pressedOK = false;
    private JDialog dialog;

    EntityDialog() {
        panel.setLayout(new MigLayout("wrap 2"));
        okButton.setEnabled(false);
        okButton.addActionListener((a) -> {
            dialog.setVisible(false);
            pressedOK = true;
        });
        cancelButton.addActionListener((a) -> dialog.setVisible(false));
    }

    public Optional<E> show(JComponent parentComponent, String title, String okText) {
        okButton.setText(okText);
        var pressedOK = isconfirmed(title);
        return pressedOK ? Optional.of(getEntity()) : Optional.empty();
    }

    void add(String labelText, JComponent component) {
        var label = new JLabel(labelText);
        panel.add(label);
        panel.add(component, "wmin 250lp, grow");
    }

    void toggleOk(boolean state) {
        okButton.setEnabled(state);
    }

    abstract E getEntity();

    boolean isconfirmed(String title) {
        addDialogControls();
        dialog = new JDialog(Frame.getFrames()[0], title, Dialog.ModalityType.APPLICATION_MODAL);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        dialog.setResizable(false);
        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
        return pressedOK;
    }

    private void addDialogControls() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(this.panel.getSize().width, 5));
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(okButton);
        panel.add(cancelButton);
        add("", panel);
    }
}
