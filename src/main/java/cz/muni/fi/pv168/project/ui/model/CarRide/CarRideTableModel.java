package cz.muni.fi.pv168.project.ui.model.CarRide;

import cz.muni.fi.pv168.project.entities.CarRide;
import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
public class CarRideTableModel extends TableModel<CarRide> {

    public CarRideTableModel(Collection<CarRide> carRides) {
        super(carRides, List.of(
                Column.editable("Date", LocalDateTime.class, CarRide::getDate, CarRide::setDate),
                Column.editable("First name", String.class, CarRide::getTitle, CarRide::setTitle),
                Column.editable("Distance", Double.class, CarRide::getDistance, CarRide::setDistance),
                Column.editable("Department", Category.class, CarRide::getCategory, CarRide::setCategory)));
    }
}
