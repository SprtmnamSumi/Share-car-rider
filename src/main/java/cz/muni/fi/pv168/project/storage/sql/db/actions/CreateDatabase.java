package cz.muni.fi.pv168.project.storage.sql.db.actions;


import cz.muni.fi.pv168.project.data.Initializer;
import cz.muni.fi.pv168.project.storage.sql.db.DatabaseManager;

import static cz.muni.fi.pv168.project.wiring.Injector.getInjector;

public final class CreateDatabase {

    public static void main(String[] args) {
        var injector = getInjector();
        var dbManager = injector.getInstance(DatabaseManager.class);
        dbManager.initSchema();

        var initializer = injector.getInstance(Initializer.class);
        initializer.initialize(15);

        System.out.println("Database created...");
        System.out.println("Database connection string: " + dbManager.getDatabaseConnectionString());

    }
}
