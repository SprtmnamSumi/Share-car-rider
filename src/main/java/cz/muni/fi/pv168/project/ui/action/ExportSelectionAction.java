package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.business.model.Model;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.dialog.ExportDialog;
import cz.muni.fi.pv168.project.ui.icons.CachedIconLoader;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.model.TableUtils;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public final class ExportSelectionAction extends AbstractAction {
    private final DialogFactory dialogFactory;
    private final JTable table;

    ExportSelectionAction(DialogFactory dialogFactory, JTable table) {
        super("Export");
        this.dialogFactory = dialogFactory;
        this.table = table;
        putValue(SMALL_ICON, new CachedIconLoader().getIcon("export.png"));
        putValue(SHORT_DESCRIPTION, "Exports selection");
        putValue(MNEMONIC_KEY, KeyEvent.VK_X);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ExportDialog popupDialog = dialogFactory.getExportDialog(TableUtils.getSelectedData(table));
        popupDialog.setSize(400, 200);
        popupDialog.setLocationRelativeTo(null);
        popupDialog.setVisible(true);
    }
}
