package cz.muni.fi.pv168.project.storage.sql.dao;


import cz.muni.fi.pv168.project.storage.sql.db.ConnectionHandler;
import cz.muni.fi.pv168.project.storage.sql.entity.CurrencyEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.TemplateEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import javax.inject.Inject;

/**
 * DAO for {@link TemplateEntity} entity.
 */
public final class CurrencyDao extends CrudDao<CurrencyEntity> implements DataAccessObject<CurrencyEntity> {

    @Inject
    CurrencyDao(Supplier<ConnectionHandler> connections) {
        super(connections);
        super.setdataAccess(this);
    }

    protected CurrencyEntity entityFromResult(ResultSet resultSet) throws SQLException {
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

        ISetUp<PreparedStatement, SQLException> statementSetup = (PreparedStatement statement) -> {
            statement.setString(1, newTemplate.getGuid());
            statement.setString(2, newTemplate.getName());
            statement.setString(3, newTemplate.getSymbol());
            statement.setDouble(4, newTemplate.getNewestRateToDollar());
        };

        return super.create(newTemplate, sql, statementSetup);

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
        return super.findAll(sql);
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
        return super.findById(id, sql);
    }

    @Override
    public Optional<CurrencyEntity> findByGuid(String guid) {
        var sql = """
                SELECT id,
                guid,
                       name,
                       symbol,
                       newestRateToDollar
                FROM Currency
                WHERE guid = ?
                """;
        return super.findByGuid(guid, sql);
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

        ISetUp<PreparedStatement, SQLException> statementSetup = (PreparedStatement statement) -> {
            statement.setString(1, entity.getGuid());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getSymbol());
            statement.setDouble(4, entity.getNewestRateToDollar());
        };
        return super.update(entity, sql, statementSetup);
    }

    @Override
    public void deleteByGuid(String guid) {
        var sql = """
                DELETE FROM Currency
                WHERE guid = ?
                """;
        super.deleteByGuid(guid, sql);
    }

    @Override
    public void deleteAll() {
        var sql = "DELETE FROM Currency";
        super.deleteAll(sql);
    }

    @Override
    public boolean existsByGuid(String guid) {
        var sql = """
                SELECT id
                FROM Currency
                WHERE guid = ?
                """;
        return super.existsByGuid(guid, sql);
    }
}
