package cz.muni.fi.pv168.project.storage.sql.entity.mapper;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.storage.sql.dao.DataAccessObject;
import cz.muni.fi.pv168.project.storage.sql.dao.DataStorageException;
import cz.muni.fi.pv168.project.storage.sql.entity.CategoryEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.CurrencyEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.TemplateEntity;
import javax.inject.Inject;

public class TemplateMapper extends Mapper<TemplateEntity, Template> implements EntityMapper<TemplateEntity, Template> {


    private final DataAccessObject<CategoryEntity> departmentDao;
    private final EntityMapper<CategoryEntity, Category> departmentMapper;
    private final DataAccessObject<CurrencyEntity> currencyDao;
    private final EntityMapper<CurrencyEntity, Currency> currencyMapper;

    @Inject
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
                .findById(entity.getCategoryId())
                .map(departmentMapper::mapToBusiness)
                .orElseThrow(() -> new DataStorageException("Department not found, id: " +
                        entity.getCategoryId()));

        var currency = currencyDao
                .findById(entity.getCategoryId())
                .map(currencyMapper::mapToBusiness)
                .orElseThrow(() -> new DataStorageException("Department not found, id: " +
                        entity.getCategoryId()));


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

        var categoryEntity = departmentDao
                .findByGuid(entity.getCategory().getGuid())
                .orElseThrow(() -> new DataStorageException("Department not found, guid: " +
                        entity.getCategory().getGuid()));

        var currencyEntity = currencyDao
                .findByGuid(entity.getCurrency().getGuid())
                .orElseThrow(() -> new DataStorageException("Department not found, guid: " +
                        entity.getCategory().getGuid()));

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
                categoryEntity.getId(),
                currencyEntity.getId(),
                entity.getConversionToDollars());
    }
}
