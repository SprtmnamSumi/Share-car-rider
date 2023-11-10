package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.gui.dialog.ImportDialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ImportAction extends AbstractAction {

    private BufferedImage importPicture;

    public ImportAction() {
        super("Import");
        try {
            importPicture = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/import.png"));
            Icon customIcon = new ImageIcon(importPicture);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        putValue(SHORT_DESCRIPTION, "Imports data");
        putValue(MNEMONIC_KEY, KeyEvent.VK_I);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ImportDialog popupDialog = new ImportDialog(new JFrame("Popup"), "str");
        popupDialog.setSize(300, 200);

        // Center the custom dialog on the screen
        popupDialog.setLocationRelativeTo(null);

        // Make the cus tom dialog visible
        popupDialog.setVisible(true);
    }
}
