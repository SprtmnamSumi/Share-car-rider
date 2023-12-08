package cz.muni.fi.pv168.project.ui.action.Currency;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.data.EntityProvider;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.icons.IconLoader;

import javax.inject.Inject;
import javax.swing.Action;
import javax.swing.JTable;

public class CurrencyActionFactory implements DefaultActionFactory<Currency> {
    private final DialogFactory dialogFactory;
    private final EntityProvider entityProvider;
    private final IconLoader iconLoader;
    @Inject
    CurrencyActionFactory(DialogFactory dialogFactory, EntityProvider entityProvider, IconLoader iconLoader) {
        this.dialogFactory = dialogFactory;
        this.entityProvider = entityProvider;
        this.iconLoader = iconLoader;
    }

    @Override
    public Action getAddAction(JTable table) {
        return new AddCurrencyAction(table, dialogFactory, entityProvider, iconLoader.getIcon("add.png"));
    }

    @Override
    public Action getDeleteAction(JTable table) {
        throw new UnsupportedOperationException("Delete action is not supported");
    }

    @Override
    public Action getEditAction(JTable table) {
        throw new UnsupportedOperationException("Edit action is not supported");
    }

    public Action getChooseAction(JTable table) {
        return new AddCurrencyAction(table, dialogFactory, entityProvider, iconLoader.getIcon("coin.png"));
    }
}
