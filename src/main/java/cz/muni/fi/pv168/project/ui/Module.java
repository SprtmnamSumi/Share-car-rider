package cz.muni.fi.pv168.project.ui;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.storage.sql.db.DatabaseManager;
import cz.muni.fi.pv168.project.wiring.ProductionDatabaseProvider;


public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(MainWindow.class).to(MainWindowImpl.class);
        bind(new TypeLiteral<DatabaseManager>() {
        }).toInstance(ProductionDatabaseProvider.getDatabaseManager());
    }
}
