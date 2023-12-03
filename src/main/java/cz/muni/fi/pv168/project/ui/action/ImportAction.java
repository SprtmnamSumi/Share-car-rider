package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.data.ImportInitializator;
import cz.muni.fi.pv168.project.ui.dialog.ImportDialog;
import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public final class ImportAction extends AbstractAction {

    private final CarRideTableFilter carRideTableFilter;
    private final TableModel<Template> templates;
    private final TableModel<Currency> currencies;
    private final TableModel<Category> categories;
    private BufferedImage importPicture;
    private ImportInitializator im;

    public ImportAction(CarRideTableFilter carRideTableFilte, TableModel<Template> templates, TableModel<Currency> currencies, TableModel<Category> categories, ImportInitializator importInitializator) {
        super("Import");
        this.carRideTableFilter = carRideTableFilte;
        this.templates = templates;
        this.currencies = currencies;
        this.categories = categories;
        this.im = importInitializator;
        try {
            importPicture = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/import.png"));
            Icon customIcon = new ImageIcon(importPicture);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        putValue(SHORT_DESCRIPTION, "Imports data");
        putValue(MNEMONIC_KEY, KeyEvent.VK_I);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ImportDialog popupDialog = new ImportDialog(new JFrame("Popup"), "str", templates, currencies, categories, carRideTableFilter, im);
        popupDialog.setSize(300, 200);

        // Center the custom dialog on the screen
        popupDialog.setLocationRelativeTo(null);

        // Make the cus tom dialog visible
        popupDialog.setVisible(true);
    }
}
