package cz.muni.fi.pv168.project.ui.action;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class SettingsAction extends AbstractAction {

    private BufferedImage settingsPicture;

    public SettingsAction() {
        super("Settings");
        try {
            settingsPicture = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/settings.png"));
            Icon customIcon = new ImageIcon(settingsPicture);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        putValue(SHORT_DESCRIPTION, "Settings");
        putValue(MNEMONIC_KEY, KeyEvent.VK_S);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
