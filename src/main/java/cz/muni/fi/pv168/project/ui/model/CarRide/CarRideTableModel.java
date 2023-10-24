package cz.muni.fi.pv168.project.ui.model.CarRide;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.service.crud.ICarRideICrudService;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
public class CarRideTableModel extends TableModel<CarRide> {

    @Inject
    public CarRideTableModel(ICarRideICrudService crudService) {
        super(crudService, List.of(
                Column.editable("Date", LocalDateTime.class, CarRide::getDate, CarRide::setDate),
                Column.editable("Name", String.class, CarRide::getTitle, CarRide::setTitle),
                Column.editable("Distance", Double.class, CarRide::getDistance, CarRide::setDistance),
                Column.editable("Category", Category.class, CarRide::getCategory, CarRide::setCategory)
        ));
    }
}
