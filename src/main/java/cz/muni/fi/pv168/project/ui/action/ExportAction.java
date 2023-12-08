package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.dialog.ExportDialog;
import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.icons.CachedIconLoader;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class ExportAction extends AbstractAction {

    private final CarRideTableFilter carRideTableFilter;
    private final TableModel<Template> templates;
    private final TableModel<Currency> currencies;
    private final TableModel<Category> categories;

    public ExportAction(CarRideTableFilter carRideTableFilte, TableModel<Template> templates, TableModel<Currency> currencies, TableModel<Category> categories) {
        super("Export");
        this.carRideTableFilter = carRideTableFilte;
        this.templates = templates;
        this.currencies = currencies;
        this.categories = categories;
        putValue(SMALL_ICON, new CachedIconLoader().getIcon("export.png"));
        putValue(SHORT_DESCRIPTION, "Exports data");
        putValue(MNEMONIC_KEY, KeyEvent.VK_X);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ExportDialog popupDialog = new ExportDialog(new JFrame("Popup"), carRideTableFilter, templates, currencies, categories);
        popupDialog.setSize(400, 200);

        popupDialog.setLocationRelativeTo(null);
        popupDialog.setVisible(true);
    }
}
