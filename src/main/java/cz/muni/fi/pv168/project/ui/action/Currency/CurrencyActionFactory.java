package cz.muni.fi.pv168.project.ui.action.Currency;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.icons.IconLoader;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;

import javax.inject.Inject;
import javax.swing.*;

public class CurrencyActionFactory implements DefaultActionFactory<Currency> {
    private final DialogFactory dialogFactory;
    private final IconLoader iconLoader;

    @Inject
    CurrencyActionFactory(DialogFactory dialogFactory, IconLoader iconLoader) {
        this.dialogFactory = dialogFactory;
        this.iconLoader = iconLoader;
    }

    @Override
    public Action getAddAction(JTable table) {
        return new AddCurrencyAction(table, dialogFactory, iconLoader.getIcon("add.png"));
    }

    @Override
    public Action getDeleteAction(JTable table) {
        return null;
    }

    @Override
    public Action getEditAction(JTable table) {
        return null;
    }

    public Action getChooseAction(JTable table) {
        return new ChooseCurrencyAction(table, dialogFactory, this, iconLoader.getIcon("coin.png"));
    }
}
