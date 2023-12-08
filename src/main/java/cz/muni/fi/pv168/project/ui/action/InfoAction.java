package cz.muni.fi.pv168.project.ui.action;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class InfoAction extends AbstractAction {

    private BufferedImage infoPicture;

    public InfoAction() {
        super("Info", null);
        try {
            infoPicture = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/info.png"));
            Icon customIcon = new ImageIcon(infoPicture);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        putValue(SHORT_DESCRIPTION, "Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
                "This cover has been designed using images from Flaticon.com." + System.lineSeparator()
                        + System.lineSeparator()
                        + "This app is credited to these creators:" + System.lineSeparator()
                        + "Ondřej Man" + System.lineSeparator()
                        + "Marek Horský" + System.lineSeparator()
                        + "Jan Šmíd" + System.lineSeparator()
                        + "Sabrina Orálková");
    }

    public static final class currenciesAction extends AbstractAction {


        public currenciesAction() {
            super("Currencies", null);
            putValue(SHORT_DESCRIPTION, "Currencies");

        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}