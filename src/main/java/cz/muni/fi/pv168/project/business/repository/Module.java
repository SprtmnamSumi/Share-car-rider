package cz.muni.fi.pv168.project.business.repository;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.storage.memory.InMemoryRepository;
import cz.muni.fi.pv168.project.storage.sql.SqlRepository;
import cz.muni.fi.pv168.project.storage.sql.dao.CategoryCrudDao;
import cz.muni.fi.pv168.project.storage.sql.dao.CurrencyDao;
import cz.muni.fi.pv168.project.storage.sql.db.TransactionConnectionSupplier;
import cz.muni.fi.pv168.project.storage.sql.db.TransactionManagerImpl;
import cz.muni.fi.pv168.project.storage.sql.entity.mapper.CategoryMapper;
import cz.muni.fi.pv168.project.storage.sql.entity.mapper.CurrencyMapper;
import cz.muni.fi.pv168.project.wiring.ProductionDatabaseProvider;
import java.util.ArrayList;

public class Module extends AbstractModule {
    @Override
    protected void configure() {

        var databaseManager = ProductionDatabaseProvider.getDatabaseManager();
        var transactionManager = new TransactionManagerImpl(databaseManager);
        var transactionConnectionSupplier = new TransactionConnectionSupplier(transactionManager, databaseManager);

        var departmentMapper = new CategoryMapper();
        var departmentDao = new CategoryCrudDao(transactionConnectionSupplier);

        var currencyMapper = new CurrencyMapper();
        var currencyDao = new CurrencyDao(transactionConnectionSupplier);


        bind(new TypeLiteral<Repository<Template>>() {
        }).toInstance(new InMemoryRepository<>(new ArrayList<Template>()));
        bind(new TypeLiteral<Repository<CarRide>>() {
        }).toInstance(new InMemoryRepository<>(new ArrayList<CarRide>()));

        bind(new TypeLiteral<Repository<Category>>() {
        })
                .toInstance(
                        new SqlRepository<>(departmentDao, departmentMapper)
                );

        bind(new TypeLiteral<Repository<Currency>>() {
        })
                .toInstance(
                        new SqlRepository<>(currencyDao, currencyMapper)
                );
    }
}
