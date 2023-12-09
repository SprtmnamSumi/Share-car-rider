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
                long entityId;

                if (keyResultSet.next()) {
                    entityId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + entity);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + entity);
                }

                return dataAccess.findById(entityId).orElseThrow();
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
            List<T> entities = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var entity = entityFromResult(resultSet);
                    entities.add(entity);
                }
            }

            return entities;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all entities" + ex);
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
                // Entity not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load entity by id: " + id, ex);
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
                // Entity not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load entity by guid: " + guid, ex);
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
                throw new DataStorageException("Entity not found, id: " + entity.getId());
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 Entity (rows=%d) has been updated: %s"
                        .formatted(rowsUpdated, entity));
            }
            return entity;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to update Entity: " + entity, ex);
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
                throw new DataStorageException("Entity not found, guid: " + guid);
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 Entity (rows=%d) has been deleted: %s"
                        .formatted(rowsUpdated, guid));
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete Entity, guid: " + guid, ex);
        }
    }

    public void deleteAll(String sql) {
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete all entities", ex);
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
            throw new DataStorageException("Failed to check if entity exists, guid: " + guid, ex);
        }
    }
}
