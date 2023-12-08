package cz.muni.fi.pv168.project.storage.sql.dao;


import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.storage.sql.db.ConnectionHandler;
import cz.muni.fi.pv168.project.storage.sql.entity.CategoryEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import javax.inject.Inject;

/**
 * DAO for {@link Category} entity.
 */
public final class CategoryCrudDao extends CrudDao<CategoryEntity> implements DataAccessObject<CategoryEntity> {

    @Inject
    public CategoryCrudDao(Supplier<ConnectionHandler> connections) {
        super(connections);
        super.setdataAccess(this);
    }

    protected CategoryEntity entityFromResult(ResultSet resultSet) throws SQLException {
        return new CategoryEntity(
                resultSet.getLong("id"),
                resultSet.getString("guid"),
                resultSet.getString("name"),
                resultSet.getInt("colour")
        );
    }

    @Override
    public CategoryEntity create(CategoryEntity newCategory) {
        var sql = """
                INSERT INTO Category(
                    guid,
                    name,
                    colour
                )
                VALUES (?, ?, ?);
                """;

        ISetUp<PreparedStatement, SQLException> sayHello = (PreparedStatement statement) -> {
            statement.setString(1, newCategory.getGuid());
            statement.setString(2, newCategory.getName());
            statement.setInt(3, newCategory.getColour());
        };

        return super.create(newCategory, sql, sayHello);
    }

    @Override
    public Collection<CategoryEntity> findAll() {
        var sql = """
                SELECT id,
                       guid,
                       name,
                       colour
                FROM Category
                """;
        return super.findAll(sql);
    }

    @Override
    public Optional<CategoryEntity> findById(long id) {
        var sql = """
                SELECT id,
                       guid,
                       name,
                       colour
                FROM Category
                WHERE id = ?
                """;
        return super.findById(id, sql);
    }

    @Override
    public Optional<CategoryEntity> findByGuid(String guid) {
        var sql = """
                SELECT id,
                       guid,
                       name,
                       colour
                FROM Category
                WHERE guid = ?
                """;
        return super.findByGuid(guid, sql);
    }

    @Override
    public CategoryEntity update(CategoryEntity entity) {
        var sql = """
                UPDATE Category
                SET name = ?,
                    colour = ?
                WHERE id = ?
                """;

        ISetUp<PreparedStatement, SQLException> sayHello = (PreparedStatement statement) -> {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getColour());
            statement.setLong(3, entity.getId());
        };
        return super.update(entity, sql, sayHello);
    }

    @Override
    public void deleteByGuid(String guid) {
        var sql = """
                DELETE FROM Category
                WHERE guid = ?
                """;
        super.deleteByGuid(guid, sql);
    }

    @Override
    public void deleteAll() {
        var sql = "DELETE FROM Category";
        super.deleteAll(sql);
    }

    @Override
    public boolean existsByGuid(String guid) {
        var sql = """
                SELECT id
                FROM Category
                WHERE guid = ?
                """;
        return super.existsByGuid(guid, sql);
    }

}
