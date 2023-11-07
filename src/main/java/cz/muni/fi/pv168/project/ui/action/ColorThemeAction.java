package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.business.service.properties.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;

public final class ColorThemeAction extends AbstractAction {
    public ColorThemeAction() {
        super("Change color theme", null);
        putValue(SHORT_DESCRIPTION, "Change color theme");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Config.changeColorThemeProperty();
        JOptionPane.showMessageDialog(null, "Color theme has been changed. Please restart the application to apply changes.");
    }
}
