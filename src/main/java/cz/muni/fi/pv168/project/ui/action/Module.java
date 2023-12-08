package cz.muni.fi.pv168.project.ui.action;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.action.CarRide.CarRideActionFactory;
import cz.muni.fi.pv168.project.ui.action.Category.CategoryActionFactory;
import cz.muni.fi.pv168.project.ui.action.Currency.CurrencyActionFactory;
import cz.muni.fi.pv168.project.ui.action.Templates.TemplateActionFactory;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<DefaultActionFactory<CarRide>>() {
        }).to(CarRideActionFactory.class);
        bind(new TypeLiteral<DefaultActionFactory<Category>>() {
        }).to(CategoryActionFactory.class);
        bind(new TypeLiteral<DefaultActionFactory<Template>>() {
        }).to(TemplateActionFactory.class);
        bind(new TypeLiteral<DefaultActionFactory<Currency>>() {
        }).to(CurrencyActionFactory.class);
    }
}
