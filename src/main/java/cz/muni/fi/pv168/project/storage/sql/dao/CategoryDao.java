package cz.muni.fi.pv168.project.storage.sql.dao;


import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.storage.sql.db.ConnectionHandler;
import cz.muni.fi.pv168.project.storage.sql.entity.CategoryEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * DAO for {@link Category} entity.
 */
public final class CategoryDao implements DataAccessObject<CategoryEntity> {

    private final Supplier<ConnectionHandler> connections;

    public CategoryDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    private static CategoryEntity categoriesFromResultSet(ResultSet resultSet) throws SQLException {
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
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, newCategory.guid());
            statement.setString(2, newCategory.name());
            statement.setInt(3, newCategory.colour());
            statement.executeUpdate();

            try (var keyResultSet = statement.getGeneratedKeys()) {
                long employeeId;

                if (keyResultSet.next()) {
                    employeeId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + newCategory);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + newCategory);
                }

                return findById(employeeId).orElseThrow();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + newCategory, ex);
        }
    }

    @Override
    public Collection<CategoryEntity> findAll() {
        var sql = """
                SELECT id,
                       guid,
                       name,
                       colour
                FROM Department
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            List<CategoryEntity> departments = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var department = categoriesFromResultSet(resultSet);
                    departments.add(department);
                }
            }

            return departments;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all departments", ex);
        }
    }

    @Override
    public Optional<CategoryEntity> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<CategoryEntity> findByGuid(String guid) {
        return Optional.empty();
    }

    @Override
    public CategoryEntity update(CategoryEntity entity) {
        return null;
    }

    @Override
    public void deleteByGuid(String guid) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean existsByGuid(String guid) {
        return false;
    }

}
