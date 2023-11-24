package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.action.Currency.CurrencyActionFactory;
import javax.swing.JTable;

public interface DialogFactory {

    EntityDialog<CarRide> getAddCarRideDialog(CarRide carRide);

    EntityDialog<Category> getAddCategoryDialog(Category category);

    EntityDialog<Template> getAddTemplateDialog(Template template);

    EntityDialog<Currency> getAddCurrencyDialog(Currency currency);

    EntityDialog<Currency> getChooseCurrencyDialog(JTable table, CurrencyActionFactory currencyActionFactory);
}
