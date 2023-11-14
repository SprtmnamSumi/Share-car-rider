package cz.muni.fi.pv168.project.ui.action.Templates;

import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.icons.IconLoader;

import javax.inject.Inject;
import javax.swing.*;

public class TemplateActionFactory implements DefaultActionFactory<Template> {
    private final DialogFactory dialogFactory;
    private final IconLoader iconLoader;

    @Inject
    TemplateActionFactory(DialogFactory dialogFactory, IconLoader iconLoader) {
        this.dialogFactory = dialogFactory;
        this.iconLoader = iconLoader;
    }

    public Action getAddAction(JTable table) {
        return new AddTemplateAction(table, dialogFactory, iconLoader.getIcon("add.png"));
    }

    public Action getDeleteAction(JTable table) {
        return new DeleteTemplateAction(table, iconLoader.getIcon("bin.png"));
    }

    public Action getEditAction(JTable table) {
        return new EditTemplateAction(table, dialogFactory, iconLoader.getIcon("editing.png"));
    }
}
