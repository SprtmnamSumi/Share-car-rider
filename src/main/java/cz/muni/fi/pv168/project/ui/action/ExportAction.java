package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.ui.dialog.ExportDialog;
import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ExportAction extends AbstractAction {

    private BufferedImage exportPicture;
    private final CarRideTableFilter carRideTableFilter;

    public ExportAction(CarRideTableFilter carRideTableFilte) {
        super("Export");
        this.carRideTableFilter = carRideTableFilte;
        try {
            exportPicture = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/export.png"));
            Icon customIcon = new ImageIcon(exportPicture);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        putValue(SHORT_DESCRIPTION, "Exports data");
        putValue(MNEMONIC_KEY, KeyEvent.VK_X);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ExportDialog popupDialog = new ExportDialog(new JFrame("Popup"), "str", carRideTableFilter);
        popupDialog.setSize(400, 200);

        popupDialog.setLocationRelativeTo(null);
        popupDialog.setVisible(true);
    }
}
