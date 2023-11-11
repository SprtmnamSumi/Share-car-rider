package cz.muni.fi.pv168.project.ui.action.Currency;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.dialog.AddCurrencyDialog;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public final class AddCurrencyAction extends AbstractAction {
    private final JTable currencyTable;
    private BufferedImage addImage;

    public AddCurrencyAction(JTable currencyTable) {
        super("Add");

        try {
            addImage = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/money.png"));
            Icon customIcon = new ImageIcon(addImage);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.currencyTable = currencyTable;
        putValue(SHORT_DESCRIPTION, "Adds new Currency");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currencyTableModel = (CurrencyTableModel) currencyTable.getModel();
        var dialog = new AddCurrencyDialog(new Currency("", "", new LinkedList<>() {
        }));
        dialog.show(currencyTable, "Add Currency")
                .ifPresent(currencyTableModel::addRow);
    }
}
