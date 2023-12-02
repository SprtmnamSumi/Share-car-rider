package cz.muni.fi.pv168.project.storage.sql.dao;

import cz.muni.fi.pv168.project.storage.sql.db.ConnectionHandler;
import cz.muni.fi.pv168.project.storage.sql.entity.Entity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class CrudDao<T extends Entity> {

    protected final Supplier<ConnectionHandler> connections;
    private DataAccessObject<T> dataAccess;

    public CrudDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    protected void setdataAccess(DataAccessObject<T> dataAccess) {
        this.dataAccess = dataAccess;
    }

    abstract T entityFromResult(ResultSet resultSet) throws SQLException;

    public T create(T entity, String sql, ISetUp<PreparedStatement, SQLException> setUp) {
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            setUp.apply(statement);
            statement.executeUpdate();

            try (var keyResultSet = statement.getGeneratedKeys()) {
                long employeeId;

                if (keyResultSet.next()) {
                    employeeId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + entity);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + entity);
                }

                return dataAccess.findById(employeeId).orElseThrow();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + entity, ex);
        }
    }


    public Collection<T> findAll(String sql) {
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            List<T> departments = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var Category = entityFromResult(resultSet);
                    departments.add(Category);
                }
            }

            return departments;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all departments", ex);
        }
    }


    public Optional<T> findById(long id, String sql) {
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(entityFromResult(resultSet));
            } else {
                // Category not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load Category by id: " + id, ex);
        }
    }


    public Optional<T> findByGuid(String guid, String sql) {

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, guid);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(entityFromResult(resultSet));
            } else {
                // Category not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load Category by guid: " + guid, ex);
        }
    }


    public T update(T entity, String sql, ISetUp<PreparedStatement, SQLException> setUp) {
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            setUp.apply(statement);
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


    public void deleteByGuid(String guid, String sql) {
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

    public void deleteAll(String sql) {
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete all departments", ex);
        }
    }


    public boolean existsByGuid(String guid, String sql) {
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
