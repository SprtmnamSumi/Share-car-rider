package cz.muni.fi.pv168.project.ui.action.CarRide;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;

import javax.inject.Inject;
import javax.swing.*;

public class CarRideActionFactory implements DefaultActionFactory<CarRide> {
    private final EntityListModelAdapter<Category> categoryListModel;
    private final EntityListModelAdapter<Template> templateListModel;
    @Inject
    public CarRideActionFactory(EntityListModelAdapter<Category> categoryListModel, EntityListModelAdapter<Template> templateListModel){
        this.categoryListModel = categoryListModel;
        this.templateListModel = templateListModel;
    }

    public Action getAddAction(JTable table){
        return new AddCarRideAction(table, categoryListModel, templateListModel);
    }
    public Action getDeleteAction(JTable table){
        return new DeleteCarRideAction(table);
    }
    public Action getEditAction(JTable table){
        return new EditCarRideAction(table, categoryListModel, templateListModel);
    }
}
