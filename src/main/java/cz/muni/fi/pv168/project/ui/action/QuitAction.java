package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.ui.icons.CachedIconLoader;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class QuitAction extends AbstractAction {

    private BufferedImage quitPicture;

    public QuitAction() {
        super("Quit");
        putValue(SMALL_ICON, new CachedIconLoader().getIcon("cross.png"));
        putValue(SHORT_DESCRIPTION, "Terminates the application");
        putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
