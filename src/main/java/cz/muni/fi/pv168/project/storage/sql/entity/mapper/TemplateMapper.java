package cz.muni.fi.pv168.project.storage.sql.entity.mapper;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.storage.sql.dao.DataAccessObject;
import cz.muni.fi.pv168.project.storage.sql.dao.DataStorageException;
import cz.muni.fi.pv168.project.storage.sql.entity.CategoryEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.CurrencyEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.TemplateEntity;

public class TemplateMapper extends Mapper<TemplateEntity, Template> implements EntityMapper<TemplateEntity, Template> {


    private final DataAccessObject<CategoryEntity> departmentDao;
    private final EntityMapper<CategoryEntity, Category> departmentMapper;
    private final DataAccessObject<CurrencyEntity> currencyDao;
    private final EntityMapper<CurrencyEntity, Currency> currencyMapper;

    public TemplateMapper(DataAccessObject<CategoryEntity> categoryDao, EntityMapper<CategoryEntity, Category> categoryMapper,
                          DataAccessObject<CurrencyEntity> currencyDao, EntityMapper<CurrencyEntity, Currency> currencyMapper) {

        this.departmentDao = categoryDao;
        this.departmentMapper = categoryMapper;
        this.currencyDao = currencyDao;
        this.currencyMapper = currencyMapper;
    }

    @Override
    public Template mapToBusiness(TemplateEntity entity) {
        var category = departmentDao
                .findByGuid(entity.getCategoryGuid())
                .map(departmentMapper::mapToBusiness)
                .orElseThrow(() -> new DataStorageException("Department not found, id: " +
                        entity.getCategoryGuid()));

        var currency = currencyDao
                .findByGuid(entity.getCategoryGuid())
                .map(currencyMapper::mapToBusiness)
                .orElseThrow(() -> new DataStorageException("Department not found, id: " +
                        entity.getCategoryGuid()));


        return new Template(
                entity.getGuid(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDistance(),
                entity.getFuelConsumption(),
                entity.getCostOfFuelPerLitre(),
                entity.getNumberOfPassengers(),
                entity.getCommission(),
                category,
                currency,
                entity.getNewestConversionRate()
        );
    }

    @Override
    protected TemplateEntity getEntity(Template entity, Long dbID) {
        return new TemplateEntity(
                dbID,
                entity.getGuid(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDistance(),
                entity.getFuelConsumption(),
                entity.getCostOfFuelPerLitreInDollars(),
                entity.getNumberOfPassengers(),
                entity.getCommission(),
                entity.getCategory().getGuid(),
                entity.getCurrency().getGuid(),
                entity.getConversionToDollars());
    }
}
