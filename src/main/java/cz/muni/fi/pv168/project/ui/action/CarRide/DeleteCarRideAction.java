package cz.muni.fi.pv168.project.ui.action.CarRide;

import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

final class DeleteCarRideAction extends AbstractAction {

    private final JTable templateTable;
    private BufferedImage deleteImage;

    DeleteCarRideAction(JTable templateTable) {
        super("Delete");

        try {
            deleteImage = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/bin.png"));
            Icon customIcon = new ImageIcon(deleteImage);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        };

        this.templateTable = templateTable;
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
