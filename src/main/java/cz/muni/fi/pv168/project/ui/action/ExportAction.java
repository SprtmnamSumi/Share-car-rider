package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.dialog.ExportDialog;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;
import cz.muni.fi.pv168.project.ui.icons.CachedIconLoader;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class ExportAction extends AbstractAction {
    private final DialogFactory dialogFactory;
    private final ICarRideTableFilter carRideFilterModel;

    ExportAction(DialogFactory dialogFactory, ICarRideTableFilter carRideFilterModel) {
        super("Export");
        this.dialogFactory = dialogFactory;
        this.carRideFilterModel = carRideFilterModel;
        putValue(SMALL_ICON, new CachedIconLoader().getIcon("export.png"));
        putValue(SHORT_DESCRIPTION, "Exports data");
        putValue(MNEMONIC_KEY, KeyEvent.VK_X);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ExportDialog popupDialog = dialogFactory.getExportDialog(carRideFilterModel);
        popupDialog.setSize(400, 200);
        popupDialog.setLocationRelativeTo(null);
        popupDialog.setVisible(true);
    }
}
