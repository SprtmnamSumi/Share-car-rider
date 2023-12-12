package cz.muni.fi.pv168.project.storage.sql.db;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.storage.sql.dao.DataStorageException;
import cz.muni.fi.pv168.project.ui.model.table.CurrencyTableModel;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import org.h2.jdbcx.JdbcConnectionPool;
import org.tinylog.Logger;

import javax.sql.DataSource;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;


/**
 * The class is responsible for managing H2 database connection and schemas
 */
public final class DatabaseManager {
    private static final String PROJECT_NAME = "share-car-rider";
    private static final String DB_PROPERTIES_STRING = "DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false";
    private static TableModel<CarRide> carRideTableModel;
    private static TableModel<Category> categoryTableModel;
    private static TableModel<Template> templateTableModel;
    private static CurrencyTableModel currencyTableModel;
    private static GuidProvider guidProvider;

    private final DataSource dataSource;
    private final SqlFileExecutor sqlFileExecutor;
    private final String databaseConnectionString;

    private DatabaseManager(String jdbcUri) {
        // connection pool with empty credentials
        this.databaseConnectionString = jdbcUri;
        this.dataSource = JdbcConnectionPool.create(jdbcUri, "", "");

        this.sqlFileExecutor = new SqlFileExecutor(this::getTransactionHandler, DatabaseManager.class);
    }

    public static DatabaseManager createProductionInstance() {
        String connectionString = "jdbc:h2:%s;%s".formatted(createDbFileSystemPath(), DB_PROPERTIES_STRING);
        var dbManager = new DatabaseManager(connectionString);
        Logger.info("Database created...");
        Logger.info("Database connection string: " + dbManager.getDatabaseConnectionString());
        return dbManager;
    }

    private static Path createDbFileSystemPath() {
        String projectDir = System.getProperty("user.dir");
        Path projectDbPath = Paths.get(projectDir, "db", PROJECT_NAME);

        File parentDir = projectDbPath.getParent().toFile();

        if (parentDir.mkdirs()) {
            Logger.debug("Created a new root directory for the database: {}", projectDbPath.getParent());
        } else {
            Logger.debug("Root directory for the database already exists: {}", projectDbPath.getParent());
        }

        if (!parentDir.exists()) {
            throw new DataStorageException("Unable to create database root directory");
        }

        return projectDbPath;
    }

    public ConnectionHandler getConnectionHandler() {
        try {
            return new ConnectionHandlerImpl(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DataStorageException("Unable to get a new connection", e);
        }
    }

    public Transaction getTransactionHandler() {
        try {
            return new TransactionImpl(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DataStorageException("Unable to get a new connection", e);
        }
    }

    public String getDatabaseConnectionString() {
        return databaseConnectionString;
    }

    public void destroySchema() {
        sqlFileExecutor.execute("drop.sql");
    }

    public void initSchema() {
        sqlFileExecutor.execute("init.sql");
    }

    public void initData(String environment) {
        sqlFileExecutor.execute("data_%s.sql".formatted(environment));
    }
}

