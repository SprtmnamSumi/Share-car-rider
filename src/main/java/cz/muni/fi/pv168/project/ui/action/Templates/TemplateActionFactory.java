package cz.muni.fi.pv168.project.ui.action.Templates;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;

import javax.inject.Inject;
import javax.swing.*;

public class TemplateActionFactory implements DefaultActionFactory<Template> {
    private final EntityListModelAdapter<Category> categoryListModel;

    private final EntityListModelAdapter<Template> templateListModel;
    @Inject
    public TemplateActionFactory(EntityListModelAdapter<Category> categoryListModel, EntityListModelAdapter<Template> carRideTemplateListModel){
        this.categoryListModel = categoryListModel;
        this.templateListModel = carRideTemplateListModel;
    }

    public Action getAddAction(JTable table){
        return new AddTemplateAction(table, categoryListModel, templateListModel);
    }
    public Action getDeleteAction(JTable table){
        return new DeleteTemplateAction(table);
    }
    public Action getEditAction(JTable table){
        return new EditTemplateAction(table, categoryListModel, templateListModel);
    }
}
