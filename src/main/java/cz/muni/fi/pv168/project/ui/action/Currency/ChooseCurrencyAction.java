package cz.muni.fi.pv168.project.ui.action.Currency;


import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ChooseCurrencyAction extends AbstractAction {
    private final JTable currencyTable;
    private final DialogFactory dialogFactory;
    private BufferedImage coinImage;

    public ChooseCurrencyAction(JTable currencyTable, DialogFactory dialogFactory) {
        super("Choose currency");

        try {
            coinImage = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/coin.png"));
            Icon customIcon = new ImageIcon(coinImage);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.currencyTable = currencyTable;
        this.dialogFactory = dialogFactory;
        putValue(SHORT_DESCRIPTION, "Choose Currency");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currencyTableModel = (CurrencyTableModel) currencyTable.getModel();
        dialogFactory.getAddCurrencyDialog(new Currency("", "", 0.0))
                .show(currencyTable, "Choose Currency", "Choose")
                .ifPresent(currencyTableModel::addRow);
    }
}
