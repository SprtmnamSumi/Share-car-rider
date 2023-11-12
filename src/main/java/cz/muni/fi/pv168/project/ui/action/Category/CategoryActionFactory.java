package cz.muni.fi.pv168.project.ui.action.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;

import javax.inject.Inject;
import javax.swing.*;

public class CategoryActionFactory implements DefaultActionFactory<Category> {
    private final DialogFactory dialogFactory;
    @Inject
    CategoryActionFactory(DialogFactory dialogFactory){
        this.dialogFactory = dialogFactory;
    }

    public Action getAddAction(JTable table){
        return new AddCategoryAction(table, dialogFactory);
    }
    public Action getDeleteAction(JTable table){
        return new DeleteCategoryAction(table);
    }
    public Action getEditAction(JTable table){
        return new EditCategoryAction(table, dialogFactory);
    }
}
