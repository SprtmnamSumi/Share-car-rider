package cz.muni.fi.pv168.project.storage.sql;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.storage.sql.dao.CategoryCrudDao;
import cz.muni.fi.pv168.project.storage.sql.dao.CurrencyDao;
import cz.muni.fi.pv168.project.storage.sql.dao.DataAccessObject;
import cz.muni.fi.pv168.project.storage.sql.dao.TemplateDao;
import cz.muni.fi.pv168.project.storage.sql.db.ConnectionHandler;
import cz.muni.fi.pv168.project.storage.sql.db.DatabaseManager;
import cz.muni.fi.pv168.project.storage.sql.db.TransactionConnectionSupplier;
import cz.muni.fi.pv168.project.storage.sql.db.TransactionManager;
import cz.muni.fi.pv168.project.storage.sql.db.TransactionManagerImpl;
import cz.muni.fi.pv168.project.storage.sql.entity.CategoryEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.CurrencyEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.TemplateEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.mapper.CategoryMapper;
import cz.muni.fi.pv168.project.storage.sql.entity.mapper.CurrencyMapper;
import cz.muni.fi.pv168.project.storage.sql.entity.mapper.EntityMapper;
import cz.muni.fi.pv168.project.storage.sql.entity.mapper.TemplateMapper;
import cz.muni.fi.pv168.project.wiring.ProductionDatabaseProvider;
import java.util.function.Supplier;

public class Module extends AbstractModule {
    @Override
    protected void configure() {

        bind(new TypeLiteral<DatabaseManager>() {
        }).toInstance(ProductionDatabaseProvider.getDatabaseManager());
        bind(new TypeLiteral<TransactionManager>() {
        }).to(TransactionManagerImpl.class);
        bind(new TypeLiteral<Supplier<ConnectionHandler>>() {
        }).to(TransactionConnectionSupplier.class);

        bind(new TypeLiteral<EntityMapper<CategoryEntity, Category>>() {
        }).to(CategoryMapper.class);
        bind(new TypeLiteral<EntityMapper<CurrencyEntity, Currency>>() {
        }).to(CurrencyMapper.class);
        bind(new TypeLiteral<EntityMapper<TemplateEntity, Template>>() {
        }).to(TemplateMapper.class);

        bind(new TypeLiteral<DataAccessObject<CategoryEntity>>() {
        }).to(CategoryCrudDao.class);
        bind(new TypeLiteral<DataAccessObject<CurrencyEntity>>() {
        }).to(CurrencyDao.class);
        bind(new TypeLiteral<DataAccessObject<TemplateEntity>>() {
        }).to(TemplateDao.class);
    }
}
