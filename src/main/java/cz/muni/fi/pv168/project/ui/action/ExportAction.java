package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.gui.dialog.ExportDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public final class ExportAction extends AbstractAction {


    public ExportAction() {
        super("Export", null);
        putValue(SHORT_DESCRIPTION, "Export");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ExportDialog popupDialog = new ExportDialog(new JFrame("Popup"), "str");
        popupDialog.setSize(300, 200);

        popupDialog.setLocationRelativeTo(null);
        popupDialog.setVisible(true);
    }
}
