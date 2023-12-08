package cz.muni.fi.pv168.project.storage.sql.dao;


import cz.muni.fi.pv168.project.storage.sql.db.ConnectionHandler;
import cz.muni.fi.pv168.project.storage.sql.entity.CarRideEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.TemplateEntity;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * DAO for {@link TemplateEntity} entity.
 */
public final class CarRideDao extends CrudDao<CarRideEntity> implements DataAccessObject<CarRideEntity> {


    @Inject
    CarRideDao(Supplier<ConnectionHandler> connections) {
        super(connections);
        super.setdataAccess(this);
    }

    protected CarRideEntity entityFromResult(ResultSet resultSet) throws SQLException {
        return new CarRideEntity(
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
                resultSet.getDouble("newestConversionRateToDollar"),
                ((OffsetDateTime) resultSet.getObject("date")).toLocalDateTime()
        );
    }

    @Override
    public CarRideEntity create(CarRideEntity newTemplate) {
        var sql = """
                INSERT INTO CarRide(
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
                 newestConversionRateToDollar,
                                  date
                             )
                             VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
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

            ZoneId zone = ZoneId.of("Europe/Prague");
            OffsetDateTime odt = newTemplate.getDate().atZone(zone).toOffsetDateTime();
            statement.setObject(12, odt);
        };

        return super.create(newTemplate, sql, sayHello);

    }

    @Override
    public Collection<CarRideEntity> findAll() {
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
                 newestConversionRateToDollar,
                 date
                FROM CarRide
                """;
        return super.findAll(sql);
    }

    @Override
    public Optional<CarRideEntity> findById(long id) {
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
                 newestConversionRateToDollar,
                 date
                 FROM CarRide
                 WHERE id = ?
                 """;
        return super.findById(id, sql);
    }

    @Override
    public Optional<CarRideEntity> findByGuid(String guid) {
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
                 newestConversionRateToDollar,
                 date
                FROM CarRide
                WHERE guid = ?
                """;
        return super.findByGuid(guid, sql);
    }

    @Override
    public CarRideEntity update(CarRideEntity entity) {
        var sql = """
                UPDATE CarRide
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
                 newestConversionRateToDollar = ?,
                 date = ?
                WHERE id = ?
                """;

        ISetUp<PreparedStatement, SQLException> sayHello = (PreparedStatement statement) -> {
            statement.setString(1, entity.getGuid());
            statement.setLong(2, entity.getCurrencyId());
            statement.setString(3, entity.getTitle());
            statement.setString(4, entity.getDescription());
            statement.setDouble(5, entity.getDistance());
            statement.setDouble(6, entity.getFuelConsumption());
            statement.setDouble(7, entity.getCostOfFuelPerLitre());
            statement.setDouble(8, entity.getNumberOfPassengers());
            statement.setDouble(9, entity.getCommission());
            statement.setLong(10, entity.getCategoryId());
            statement.setDouble(11, entity.getNewestConversionRate());

            ZoneId zone = ZoneId.of("Europe/Prague");
            OffsetDateTime odt = entity.getDate().atZone(zone).toOffsetDateTime();
            statement.setObject(12, odt);
        };
        return super.update(entity, sql, sayHello);
    }

    @Override
    public void deleteByGuid(String guid) {
        var sql = """
                DELETE FROM CarRide
                WHERE guid = ?
                """;
        super.deleteByGuid(guid, sql);
    }

    @Override
    public void deleteAll() {
        var sql = "DELETE FROM CarRide";
        super.deleteAll(sql);
    }

    @Override
    public boolean existsByGuid(String guid) {
        var sql = """
                SELECT id
                FROM CarRide
                WHERE guid = ?
                """;
        return super.existsByGuid(guid, sql);
    }
}
