package cz.muni.fi.pv168.project.ui.model;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<TableModel<Category>>() {
        }).to(CategoryTableModel.class);
        bind(new TypeLiteral<TableModel<CarRide>>() {
        }).to(CarRideTableModel.class);
        bind(new TypeLiteral<TableModel<Template>>() {
        }).to(TemplateTableModel.class);

        bind(new TypeLiteral<TableModel<Currency>>() {
        }).to(CurrencyTableModel.class);
    }

    @Provides
    @Singleton
    EntityListModelAdapter<CarRide> getCarRideModelAdapter(TableModel<CarRide> carRideTableModel) {
        return new EntityListModelAdapter<>(carRideTableModel);
    }

    @Provides
    @Singleton
    EntityListModelAdapter<Category> getCategoryModelAdapter(TableModel<Category> categoryTableModel) {
        return new EntityListModelAdapter<>(categoryTableModel);
    }

    @Provides
    @Singleton
    EntityListModelAdapter<Template> getTemplateModelAdapter(TableModel<Template> templateTableModel) {
        return new EntityListModelAdapter<>(templateTableModel);
    }

    @Provides
    @Singleton
    EntityListModelAdapter<Currency> getCurrencyModelAdapter(TableModel<Currency> carRideTableModel) {
        return new EntityListModelAdapter<>(carRideTableModel);
    }
}
