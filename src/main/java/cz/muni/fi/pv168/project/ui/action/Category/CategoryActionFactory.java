package cz.muni.fi.pv168.project.ui.action.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.data.EntityProvider;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.icons.IconLoader;

import javax.inject.Inject;
import javax.swing.Action;
import javax.swing.JTable;

public class CategoryActionFactory implements DefaultActionFactory<Category> {
    private final DialogFactory dialogFactory;
    private final EntityProvider entityProvider;
    private final IconLoader iconLoader;
    @Inject
    CategoryActionFactory(DialogFactory dialogFactory, EntityProvider entityProvider, IconLoader iconLoader){
        this.dialogFactory = dialogFactory;
        this.entityProvider = entityProvider;
        this.iconLoader = iconLoader;
    }

    public Action getAddAction(JTable table){
        return new AddCategoryAction(table, dialogFactory, entityProvider, iconLoader.getIcon("add.png"));
    }
    public Action getDeleteAction(JTable table){
        return new DeleteCategoryAction(table, iconLoader.getIcon("bin.png"));
    }
    public Action getEditAction(JTable table){
        return new EditCategoryAction(table, dialogFactory, iconLoader.getIcon("editing.png"));
    }
}
