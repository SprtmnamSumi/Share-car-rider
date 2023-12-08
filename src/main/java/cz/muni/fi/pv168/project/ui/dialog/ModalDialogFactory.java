package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.currenies.CurrencyConverter;
import cz.muni.fi.pv168.project.data.ImportInitializer;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;
import javax.inject.Inject;

class ModalDialogFactory implements DialogFactory {
    private final EntityListModelAdapter<Category> categoryListModel;
    private final EntityListModelAdapter<Currency> currencyListModel;
    private final EntityListModelAdapter<Template> templateListModel;
    private final TableModel<Category> categoryTableModel;
    private final TableModel<Currency> currencyTableModel;
    private final TableModel<Template> templateTableModel;
    private final ImportInitializer importInitializer;
    private final DefaultActionFactory<Category> categoryActionFactory;
    private final CurrencyConverter currencyConverter;
    private final GuidProvider guidProvider;

    @Inject
    ModalDialogFactory(EntityListModelAdapter<Category> categoryListModel,
                       EntityListModelAdapter<Currency> currencyListModel,
                       EntityListModelAdapter<Template> templateListModel,
                       TableModel<Template> templateTableModel,
                       TableModel<Currency> currencyTableModel,
                       TableModel<Category> categoryTableModel,
                       DefaultActionFactory<Category> categoryActionFactory,
                       CurrencyConverter currencyConverter,
                       GuidProvider guidProvider,
                       ImportInitializer importInitializer) {
        this.categoryListModel = categoryListModel;
        this.currencyListModel = currencyListModel;
        this.templateListModel = templateListModel;
        this.templateTableModel = templateTableModel;
        this.currencyTableModel = currencyTableModel;
        this.categoryActionFactory = categoryActionFactory;
        this.importInitializer = importInitializer;
        this.categoryTableModel = categoryTableModel;
        this.currencyConverter = currencyConverter;
        this.guidProvider = guidProvider;
    }

    @Override
    public EntityDialog<CarRide> getAddCarRideDialog(CarRide carRide) {
        return new CarRideDialog(carRide, categoryListModel, currencyListModel, templateListModel, templateTableModel, categoryActionFactory, categoryTableModel, currencyConverter);
    }

    @Override
    public EntityDialog<Category> getAddCategoryDialog(Category category) {
        return new CategoryDialog(category);
    }

    @Override
    public EntityDialog<Template> getAddTemplateDialog(Template template) {
        return new TemplateDialog(template, categoryListModel, currencyListModel, templateListModel, currencyConverter, categoryActionFactory, categoryTableModel);
    }

    @Override
    public EntityDialog<Currency> getAddCurrencyDialog(Currency currency) {
        return new AddCurrencyDialog(guidProvider, currency);
    }

    @Override
    public ImportDialog getImportDialog() {
        return new ImportDialog(importInitializer);
    }

    @Override
    public ExportDialog getExportDialog(ICarRideTableFilter carRideFilterModel) {
        return new ExportDialog(carRideFilterModel, templateTableModel, currencyTableModel, categoryTableModel);
    }
}
