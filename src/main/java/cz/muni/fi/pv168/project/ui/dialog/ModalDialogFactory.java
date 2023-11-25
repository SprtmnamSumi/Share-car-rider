package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.currenies.CurrencyConverter;
import cz.muni.fi.pv168.project.ui.action.Currency.CurrencyActionFactory;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;
import javax.inject.Inject;
import javax.swing.JTable;

class ModalDialogFactory implements DialogFactory {
    private final EntityListModelAdapter<Category> categoryListModel;
    private final EntityListModelAdapter<Currency> currencyListModel;
    private final EntityListModelAdapter<Template> templateListModel;
    private final TableModel<Template> repository;
    private final DefaultActionFactory<Category> categoryActionFactory;
    private final CategoryTableModel categoryTableMode;
    private final CurrencyConverter currencyConverter;

    @Inject
    ModalDialogFactory(EntityListModelAdapter<Category> categoryListModel, EntityListModelAdapter<Currency> currencyListModel, EntityListModelAdapter<Template> templateListModel, TableModel<Template> repository, DefaultActionFactory<Category> categoryActionFactory, CategoryTableModel categoryTableMode, CurrencyConverter currencyConverter) {
        this.categoryListModel = categoryListModel;
        this.currencyListModel = currencyListModel;
        this.templateListModel = templateListModel;
        this.repository = repository;
        this.categoryActionFactory = categoryActionFactory;
        this.categoryTableMode = categoryTableMode;
        this.currencyConverter = currencyConverter;
    }

    @Override
    public EntityDialog<CarRide> getAddCarRideDialog(CarRide carRide) {
        return new CarRideDialog(carRide, categoryListModel, currencyListModel, templateListModel, repository, categoryActionFactory, categoryTableMode, currencyConverter);
    }

    @Override
    public EntityDialog<Category> getAddCategoryDialog(Category category) {
        return new CategoryDialog(category);
    }

    @Override
    public EntityDialog<Template> getAddTemplateDialog(Template template) {
        return new TemplateDialog(template, categoryListModel, currencyListModel, templateListModel, currencyConverter, categoryActionFactory, categoryTableMode);
    }

    @Override
    public EntityDialog<Currency> getAddCurrencyDialog(Currency currency) {
        return new AddCurrencyDialog(currency);
    }
}
