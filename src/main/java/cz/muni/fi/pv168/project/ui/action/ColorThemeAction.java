package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.business.service.properties.Config;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ColorThemeAction extends AbstractAction {

    private BufferedImage bulbPicture;

    public ColorThemeAction() {
        super("Change color theme");
        try {
            bulbPicture = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/idea.png"));
            Icon customIcon = new ImageIcon(bulbPicture);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        putValue(SHORT_DESCRIPTION, "Change color theme");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Config.changeColorThemeProperty();
        JOptionPane.showMessageDialog(null, "Color theme has been changed. Please restart the application to apply changes.");
    }
}
