package cz.muni.fi.pv168.project.ui.action.CarRide;

import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.KeyStroke;

final class DeleteCarRideAction extends AbstractAction {

    private final JTable templateTable;

    DeleteCarRideAction(JTable templateTable, Icon icon) {
        super("Delete");
        this.templateTable = templateTable;
        putValue(SMALL_ICON, icon);
        putValue(SHORT_DESCRIPTION, "Deletes Car Ride");
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var carRideTableModel = (CarRideTableModel) templateTable.getModel();
        Arrays.stream(templateTable.getSelectedRows())
                .map(templateTable::convertRowIndexToModel)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .forEach(carRideTableModel::deleteRow);
    }
}
