package cz.muni.fi.pv168.project.ui.action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public final class InfoAction extends AbstractAction {


    public InfoAction() {
        super("Info", null);
        putValue(SHORT_DESCRIPTION, "Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
