package cz.muni.fi.pv168.project.storage.sql.entity.mapper;


import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.storage.sql.entity.CategoryEntity;

/**
 * Mapper from the {@link CategoryEntity} to {@link Category}.
 */
public final class CategoryMapper extends Mapper<CategoryEntity, Category> implements EntityMapper<CategoryEntity, Category> {
    @Override
    public Category mapToBusiness(CategoryEntity dbCategory) {
        return new Category(
                dbCategory.getGuid(),
                dbCategory.getName(),
                dbCategory.getColour()
        );
    }

    @Override
    protected CategoryEntity getEntity(Category entity, Long dbID) {
        return new CategoryEntity(
                dbID,
                entity.getGuid(),
                entity.getName(),
                entity.getColour()
        );
    }
}
