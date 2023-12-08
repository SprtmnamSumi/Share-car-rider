package cz.muni.fi.pv168.project.storage.sql.dao;


import cz.muni.fi.pv168.project.storage.sql.db.ConnectionHandler;
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
public final class TemplateDao extends CrudDao<TemplateEntity> implements DataAccessObject<TemplateEntity> {


    @Inject
    public TemplateDao(Supplier<ConnectionHandler> connections) {
        super(connections);
        super.setdataAccess(this);
    }

    protected TemplateEntity entityFromResult(ResultSet resultSet) throws SQLException {
        return new TemplateEntity(
                resultSet.getLong("id"),
                resultSet.getString("guid"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getDouble("distance"),
                resultSet.getDouble("fuelConsumption"),
                resultSet.getDouble("costOfFuelPerLitre"),
                resultSet.getInt("numberOfPassengers"),
                resultSet.getDouble("commission"),
                resultSet.getLong("categoryId"),
                resultSet.getLong("currencyId"),
                resultSet.getDouble("newestConversionRateToDollar")
        );
    }

    @Override
    public TemplateEntity create(TemplateEntity newTemplate) {
        var sql = """
                INSERT INTO Template(
                 guid,
                 currencyId,
                 title,
                 description,
                 distance,
                 fuelConsumption,
                 costOfFuelPerLitre,
                 numberOfPassengers,
                 commission,
                 categoryId,
                 newestConversionRateToDollar
                             )
                             VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                             """;

        ISetUp<PreparedStatement, SQLException> sayHello = (PreparedStatement statement) -> {
            statement.setString(1, newTemplate.getGuid());
            statement.setLong(2, newTemplate.getCurrencyId());
            statement.setString(3, newTemplate.getTitle());
            statement.setString(4, newTemplate.getDescription());
            statement.setDouble(5, newTemplate.getDistance());
            statement.setDouble(6, newTemplate.getFuelConsumption());
            statement.setDouble(7, newTemplate.getCostOfFuelPerLitre());
            statement.setInt(8, newTemplate.getNumberOfPassengers());
            statement.setDouble(9, newTemplate.getCommission());
            statement.setLong(10, newTemplate.getCategoryId());
            statement.setDouble(11, newTemplate.getNewestConversionRate());
        };

        return super.create(newTemplate, sql, sayHello);

    }

    @Override
    public Collection<TemplateEntity> findAll() {
        var sql = """
                SELECT id,
                   guid,
                 currencyId,
                 title,
                 description,
                 distance,
                 fuelConsumption,
                 costOfFuelPerLitre,
                 numberOfPassengers,
                 commission,
                 categoryId,
                 newestConversionRateToDollar
                FROM Template
                """;
        return super.findAll(sql);
    }

    @Override
    public Optional<TemplateEntity> findById(long id) {
        var sql = """
                 SELECT id,
                guid,
                  currencyId,
                  title,
                  description,
                  distance,
                  fuelConsumption,
                  costOfFuelPerLitre,
                  numberOfPassengers,
                  commission,
                  categoryId,
                 newestConversionRateToDollar
                 FROM Template
                 WHERE id = ?
                 """;
        return super.findById(id, sql);
    }

    @Override
    public Optional<TemplateEntity> findByGuid(String guid) {
        var sql = """
                SELECT id,
                  guid,
                 currencyId,
                 title,
                 description,
                 distance,
                 fuelConsumption,
                 costOfFuelPerLitre,
                 numberOfPassengers,
                 commission,
                 categoryId,
                 newestConversionRateToDollar
                FROM Template
                WHERE guid = ?
                """;
        return super.findByGuid(guid, sql);
    }

    @Override
    public TemplateEntity update(TemplateEntity entity) {
        var sql = """
                UPDATE Currency
                SET    guid = ?,
                 currencyId = ?,
                 title = ?,
                 description = ?,
                 distance = ?,
                 fuelConsumption = ?,
                 costOfFuelPerLitre = ?,
                 numberOfPassengers = ?,
                 commission = ?,
                 categoryId = ?,
                 newestConversionRateToDollar = ?
                WHERE id = ?
                """;

        ISetUp<PreparedStatement, SQLException> sayHello = (PreparedStatement statement) -> {
            statement.setString(1, entity.getGuid());
            statement.setLong(2, entity.getCurrencyId());
            statement.setString(3, entity.getTitle());
            statement.setString(4, entity.getDescription());
            statement.setDouble(4, entity.getDistance());
            statement.setDouble(4, entity.getFuelConsumption());
            statement.setDouble(4, entity.getCostOfFuelPerLitre());
            statement.setDouble(4, entity.getNumberOfPassengers());
            statement.setDouble(4, entity.getCommission());
            statement.setLong(4, entity.getCategoryId());
            statement.setDouble(4, entity.getNewestConversionRate());
        };
        return super.update(entity, sql, sayHello);
    }

    @Override
    public void deleteByGuid(String guid) {
        var sql = """
                DELETE FROM Template
                WHERE guid = ?
                """;
        super.deleteByGuid(guid, sql);
    }

    @Override
    public void deleteAll() {
        var sql = "DELETE FROM Template";
        super.deleteAll(sql);
    }

    @Override
    public boolean existsByGuid(String guid) {
        var sql = """
                SELECT id
                FROM Template
                WHERE guid = ?
                """;
        return super.existsByGuid(guid, sql);
    }
}
