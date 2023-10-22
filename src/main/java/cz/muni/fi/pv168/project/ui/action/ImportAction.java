package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.gui.dialog.ImportDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public final class ImportAction extends AbstractAction {


    public ImportAction() {
        super("Import", null);
        putValue(SHORT_DESCRIPTION, "Import");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ImportDialog popupDialog = new ImportDialog(new JFrame("Popup"), "str");
        popupDialog.setSize(300, 200);

        // Center the custom dialog on the screen
        popupDialog.setLocationRelativeTo(null);

        // Make the custom dialog visible
        popupDialog.setVisible(true);
    }
}
