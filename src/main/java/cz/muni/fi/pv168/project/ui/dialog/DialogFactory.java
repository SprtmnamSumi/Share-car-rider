package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;

import javax.swing.JDialog;

public interface DialogFactory {

    EntityDialog<CarRide> getAddCarRideDialog(CarRide carRide);

    EntityDialog<Category> getAddCategoryDialog(Category category);

    EntityDialog<Template> getAddTemplateDialog(Template template);

    EntityDialog<Currency> getAddCurrencyDialog(Currency currency);
    ImportDialog getImportDialog(ICarRideTableFilter carRideFilterModel);
    ExportDialog getExportDialog(ICarRideTableFilter carRideFilterModel);
}
