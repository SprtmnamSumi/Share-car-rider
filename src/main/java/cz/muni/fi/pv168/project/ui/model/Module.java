package cz.muni.fi.pv168.project.ui.model;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<TableModel<Category>>(){}).to(CategoryTableModel.class);
        bind(new TypeLiteral<TableModel<CarRide>>(){}).to(CarRideTableModel.class);
        bind(new TypeLiteral<TableModel<Template>>(){}).to(TemplateTableModel.class);
    }
}
