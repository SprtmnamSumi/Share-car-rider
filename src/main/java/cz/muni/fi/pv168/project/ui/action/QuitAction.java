package cz.muni.fi.pv168.project.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public final class QuitAction extends AbstractAction {

    private BufferedImage quitPicture;

    public QuitAction() {
        super("Quit");
        try {
            quitPicture = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/cross.png"));
            Icon customIcon = new ImageIcon(quitPicture);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        putValue(SHORT_DESCRIPTION, "Terminates the application");
        putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
