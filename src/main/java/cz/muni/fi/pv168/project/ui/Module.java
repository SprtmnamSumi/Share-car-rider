package cz.muni.fi.pv168.project.ui;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyListModel;
import cz.muni.fi.pv168.project.ui.model.ListModel;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(MainWindow.class).to(MainWindowImpl.class);
    }
}
