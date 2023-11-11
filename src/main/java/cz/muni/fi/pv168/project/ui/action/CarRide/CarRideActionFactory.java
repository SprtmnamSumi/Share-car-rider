package cz.muni.fi.pv168.project.ui.action.CarRide;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.currenies.CurrencyConverter;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;

import javax.inject.Inject;
import javax.swing.*;

public class CarRideActionFactory implements ICarRideActionFactory {
    private final EntityListModelAdapter<Category> categoryListModel;
    private final EntityListModelAdapter<Currency> currencyListModel;
    private final EntityListModelAdapter<Template> templateListModel;
    private final TableModel<Template> repository;
    private final DefaultActionFactory<Category> categoryActionFactory;
    private final CategoryTableModel categoryTableMode;
    private final CurrencyConverter currencyConverter;

    @Inject

    public CarRideActionFactory(EntityListModelAdapter<Category> categoryListModel, EntityListModelAdapter<Currency> currencyListModel, EntityListModelAdapter<Template> templateListModel, TableModel<Template> repository, DefaultActionFactory<Category> categoryActionFactory, CategoryTableModel categoryTableMode, CurrencyConverter currencyConverter) {
        this.categoryListModel = categoryListModel;
        this.currencyListModel = currencyListModel;
        this.templateListModel = templateListModel;
        this.repository = repository;
        this.categoryActionFactory = categoryActionFactory;
        this.categoryTableMode = categoryTableMode;
        this.currencyConverter = currencyConverter;
    }

    @Override
    public Action getAddAction(JTable table) {
        return new AddCarRideAction(table, categoryListModel, currencyListModel, templateListModel, repository, categoryActionFactory, categoryTableMode, currencyConverter);
    }

    @Override
    public Action getDeleteAction(JTable table) {
        return new DeleteCarRideAction(table);
    }


    @Override
    public Action getEditAction(JTable table) {
        return new EditCarRideAction(table, categoryListModel, currencyListModel, templateListModel, repository, categoryActionFactory, categoryTableMode, currencyConverter);
    }

}
