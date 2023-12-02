package cz.muni.fi.pv168.project.storage.sql.entity.mapper;


import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.storage.sql.entity.CategoryEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.CurrencyEntity;

/**
 * Mapper from the {@link CategoryEntity} to {@link Category}.
 */
public final class CurrencyMapper extends Mapper<CurrencyEntity, Currency> implements EntityMapper<CurrencyEntity, Currency> {


    @Override
    public Currency mapToBusiness(CurrencyEntity entity) {
        return new Currency(entity.getGuid(), entity.getName(), entity.getSymbol(), entity.getNewestRateToDollar());
    }


    @Override
    CurrencyEntity getEntity(Currency entity, Long dbID) {
        return new CurrencyEntity(
                dbID,
                entity.getGuid(),
                entity.getName(),
                entity.getSymbol(),
                entity.getNewestRateToDollar()
        );
    }
}
