package cz.muni.fi.pv168.project.storage.sql.entity.mapper;


import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.storage.sql.entity.CategoryEntity;

/**
 * Mapper from the {@link CategoryEntity} to {@link Category}.
 */
public final class CategoryMapper implements EntityMapper<CategoryEntity, Category> {

    private static CategoryEntity getCategoryEntity(Category entity, Long dbId) {
        return new CategoryEntity(
                dbId,
                entity.getGuid(),
                entity.getName(),
                entity.getColour()
        );
    }

    @Override
    public Category mapToBusiness(CategoryEntity dbCategory) {
        return new Category(
                dbCategory.getGuid(),
                dbCategory.getName(),
                dbCategory.getColour()
        );
    }

    @Override
    public CategoryEntity mapNewEntityToDatabase(Category entity) {
        return getCategoryEntity(entity, null);
    }

    @Override
    public CategoryEntity mapExistingEntityToDatabase(Category entity, Long dbId) {
        return getCategoryEntity(entity, dbId);
    }
}
