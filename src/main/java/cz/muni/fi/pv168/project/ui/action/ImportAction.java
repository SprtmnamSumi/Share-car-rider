package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.ui.dialog.ImportDialog;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;
import cz.muni.fi.pv168.project.ui.icons.CachedIconLoader;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class ImportAction extends AbstractAction {
    private final DialogFactory dialogFactory;
    private final ICarRideTableFilter carRideTableFilter;
    public ImportAction(DialogFactory dialogFactory, ICarRideTableFilter carRideTableFilter) {
        super("Import");
        this.dialogFactory = dialogFactory;
        this.carRideTableFilter = carRideTableFilter;
        putValue(SMALL_ICON, new CachedIconLoader().getIcon("import.png"));
        putValue(SHORT_DESCRIPTION, "Imports data");
        putValue(MNEMONIC_KEY, KeyEvent.VK_I);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ImportDialog popupDialog = dialogFactory.getImportDialog(carRideTableFilter);
        popupDialog.setSize(300, 200);

        // Center the custom dialog on the screen
        popupDialog.setLocationRelativeTo(null);

        // Make the cus tom dialog visible
        popupDialog.setVisible(true);
    }
}
