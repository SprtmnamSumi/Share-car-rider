package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.data.ImportInitializer;
import cz.muni.fi.pv168.project.ui.dialog.ImportDialog;
import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.icons.CachedIconLoader;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class ImportAction extends AbstractAction {

    private final CarRideTableFilter carRideTableFilter;
    private final TableModel<Template> templates;
    private final TableModel<Currency> currencies;
    private final TableModel<Category> categories;
    private final ImportInitializer im;

    public ImportAction(CarRideTableFilter carRideTableFilter, TableModel<Template> templates, TableModel<Currency> currencies, TableModel<Category> categories, ImportInitializer importInitializator) {
        super("Import");
        this.carRideTableFilter = carRideTableFilter;
        this.templates = templates;
        this.currencies = currencies;
        this.categories = categories;
        this.im = importInitializator;
        putValue(SMALL_ICON, new CachedIconLoader().getIcon("import.png"));
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
