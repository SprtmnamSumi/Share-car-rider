package cz.muni.fi.pv168.project.storage.sql.entity.mapper;


import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.storage.sql.entity.CategoryEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.CurrencyEntity;

/**
 * Mapper from the {@link CategoryEntity} to {@link Category}.
 */
public final class CurrencyMapper implements EntityMapper<CurrencyEntity, Currency> {

    private static CurrencyEntity getCurrencyEntity(Currency entity, Long dbId) {
        return new CurrencyEntity(
                dbId,
                entity.getGuid(),
                entity.getName(),
                entity.getSymbol(),
                entity.getNewestRateToDollar()
        );
    }

    @Override
    public Currency mapToBusiness(CurrencyEntity entity) {
        return new Currency(entity.getGuid(), entity.getName(), entity.getSymbol(), entity.getNewestRateToDollar());
    }

    @Override
    public CurrencyEntity mapNewEntityToDatabase(Currency entity) {
        return getCurrencyEntity(entity, null);
    }

    @Override
    public CurrencyEntity mapExistingEntityToDatabase(Currency entity, Long dbId) {
        return getCurrencyEntity(entity, dbId);
    }
}
