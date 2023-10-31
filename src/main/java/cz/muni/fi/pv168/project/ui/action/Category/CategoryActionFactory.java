package cz.muni.fi.pv168.project.ui.action.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;

import javax.inject.Inject;
import javax.swing.*;

public class CategoryActionFactory implements DefaultActionFactory<Category> {
    private final EntityListModelAdapter<Category> categoryListModel;
    @Inject
    public CategoryActionFactory(EntityListModelAdapter<Category> categoryListModel){
        this.categoryListModel = categoryListModel;
    }

    public Action getAddAction(JTable table){
        return new AddCategoryAction(table, categoryListModel);
    }
    public Action getDeleteAction(JTable table){
        return new DeleteCategoryAction(table);
    }
    public Action getEditAction(JTable table){
        return new EditCategoryAction(table, categoryListModel);
    }
}
