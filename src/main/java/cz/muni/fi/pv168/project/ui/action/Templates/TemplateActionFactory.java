package cz.muni.fi.pv168.project.ui.action.Templates;

import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;

import javax.inject.Inject;
import javax.swing.*;

public class TemplateActionFactory implements DefaultActionFactory<Template> {
    private final DialogFactory dialogFactory;

    @Inject
    TemplateActionFactory(DialogFactory dialogFactory) {
        this.dialogFactory = dialogFactory;
    }

    public Action getAddAction(JTable table) {
        return new AddTemplateAction(table, dialogFactory);
    }

    public Action getDeleteAction(JTable table) {
        return new DeleteTemplateAction(table);
    }

    public Action getEditAction(JTable table) {
        return new EditTemplateAction(table, dialogFactory);
    }
}
