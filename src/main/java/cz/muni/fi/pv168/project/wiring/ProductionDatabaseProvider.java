package cz.muni.fi.pv168.project.wiring;


import cz.muni.fi.pv168.project.storage.sql.db.DatabaseManager;
import javax.inject.Singleton;

/**
 * Dependency provider for production environment.
 */
@Singleton
public final class ProductionDatabaseProvider implements IproductionDatabaseProvider {
    private static DatabaseManager databaseManager = null;

    private ProductionDatabaseProvider() {
    }

    public static DatabaseManager getDatabaseManager() {
        if (databaseManager == null) {
            createDatabaseManager();
        }
        assert databaseManager != null;

        return databaseManager;
    }


    private static void createDatabaseManager() {
        DatabaseManager databaseManager = DatabaseManager.createProductionInstance();
        databaseManager.initSchema();
        ProductionDatabaseProvider.databaseManager = databaseManager;
    }
}
