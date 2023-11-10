package cz.muni.fi.pv168.project.ui.action.Currency;


import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.dialog.ChooseCurrencyDialog;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ChooseCurrencyAction extends AbstractAction {
    private final JTable currencyTable;
    private final EntityListModelAdapter<Currency> currencyListModel;
    private BufferedImage coinImage;

    public ChooseCurrencyAction(JTable currencyTable, EntityListModelAdapter<Currency> currencyListModel) {
        super("Choose currency");

        try {
            coinImage = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/coin.png"));
            Icon customIcon = new ImageIcon(coinImage);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        };
        this.currencyTable = currencyTable;
        this.currencyListModel = currencyListModel;
        putValue(SHORT_DESCRIPTION, "Choose Currency");
    }

    public void addRow(Currency entity) {

        var entiName = entity.getName();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var dialog = new ChooseCurrencyDialog(currencyTable, currencyListModel);
        dialog.show(currencyTable, "Choose Currency")
                .ifPresent(this::addRow);
    }
}
