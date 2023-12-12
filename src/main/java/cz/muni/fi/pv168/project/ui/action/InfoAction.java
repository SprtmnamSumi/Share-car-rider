package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.ui.icons.CachedIconLoader;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public final class InfoAction extends AbstractAction {
    public InfoAction() {
        super("Info", null);
        putValue(SMALL_ICON, new CachedIconLoader().getIcon("info.png"));
        putValue(SHORT_DESCRIPTION, "Info");
    }

    @SuppressWarnings("SpellCheckingInspection")
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
}