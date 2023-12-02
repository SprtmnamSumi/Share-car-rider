package cz.muni.fi.pv168.project.storage.sql.dao;


import cz.muni.fi.pv168.project.storage.sql.db.ConnectionHandler;
import cz.muni.fi.pv168.project.storage.sql.entity.CurrencyEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.TemplateEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * DAO for {@link TemplateEntity} entity.
 */
public final class CurrencyDao implements DataAccessObject<CurrencyEntity> {

    private final Supplier<ConnectionHandler> connections;

    public CurrencyDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    private static CurrencyEntity currenciesFromResultSet(ResultSet resultSet) throws SQLException {
        return new CurrencyEntity(
                resultSet.getLong("id"),
                resultSet.getString("guid"),
                resultSet.getString("name"),
                resultSet.getString("symbol"),
                resultSet.getDouble("newestRateToDollar")
        );
    }

    @Override
    public CurrencyEntity create(CurrencyEntity newTemplate) {
        var sql = """
                INSERT INTO Currency(
                    guid,
                    name,
                    symbol,
                    newestRateToDollar
                )
                VALUES (?, ?, ?, ?);
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, newTemplate.getGuid());
            statement.setString(2, newTemplate.getName());
            statement.setString(3, newTemplate.getSymbol());
            statement.setDouble(4, newTemplate.getNewestRateToDollar());
            statement.executeUpdate();

            try (var keyResultSet = statement.getGeneratedKeys()) {
                long employeeId;

                if (keyResultSet.next()) {
                    employeeId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + newTemplate);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + newTemplate);
                }

                return findById(employeeId).orElseThrow();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + newTemplate, ex);
        }
    }

    @Override
    public Collection<CurrencyEntity> findAll() {
        var sql = """
                SELECT id, 
                guid,
                       name,
                       symbol,
                       newestRateToDollar
                FROM Currency
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            List<CurrencyEntity> departments = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var Category = currenciesFromResultSet(resultSet);
                    departments.add(Category);
                }
            }

            return departments;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all departments", ex);
        }
    }

    @Override
    public Optional<CurrencyEntity> findById(long id) {
        var sql = """
                SELECT id, 
                guid,
                       name,
                       symbol,
                       newestRateToDollar
                FROM Currency
                WHERE id = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(currenciesFromResultSet(resultSet));
            } else {
                // Category not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load Category by id: " + id, ex);
        }
    }

    @Override
    public Optional<CurrencyEntity> findByGuid(String guid) {
        var sql = """
                SELECT id,
                guid,
                       name,
                       symbol,
                       newestRateToDollar
                FROM Category
                WHERE guid = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, guid);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(currenciesFromResultSet(resultSet));
            } else {
                // Category not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load Category by guid: " + guid, ex);
        }
    }

    @Override
    public CurrencyEntity update(CurrencyEntity entity) {
        var sql = """
                UPDATE Currency
                SET guid = ?,
                    name = ?,
                    symbol  = ?,
                    newestRateToDollar = ?,
                WHERE id = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, entity.getGuid());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getSymbol());
            statement.setDouble(4, entity.getNewestRateToDollar());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Category not found, id: " + entity.getId());
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 Category (rows=%d) has been updated: %s"
                        .formatted(rowsUpdated, entity));
            }
            return entity;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to update Category: " + entity, ex);
        }
    }

    @Override
    public void deleteByGuid(String guid) {
        var sql = """
                DELETE FROM Currency
                WHERE guid = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, guid);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Category not found, guid: " + guid);
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 Category (rows=%d) has been deleted: %s"
                        .formatted(rowsUpdated, guid));
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete Category, guid: " + guid, ex);
        }
    }

    @Override
    public void deleteAll() {
        var sql = "DELETE FROM Currency";
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete all departments", ex);
        }
    }

    @Override
    public boolean existsByGuid(String guid) {
        var sql = """
                SELECT id
                FROM Currency
                WHERE guid = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, guid);
            try (var resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to check if Category exists, guid: " + guid, ex);
        }
    }
}
