package cz.muni.fi.pv168.project.ui.model.table;

import com.google.inject.Singleton;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.service.crud.ICrudService;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.inject.Inject;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
@Singleton
public class CarRideTableModel extends TableModel<CarRide> {

    @Inject
    CarRideTableModel(ICrudService<CarRide> crudService) {
        super(crudService, List.of(
                Column.readonly("Date", String.class, carRide -> carRide.getDate().toLocalDate().toString()),
                Column.readonly("Name", String.class, CarRide::getTitle),
                Column.readonly("Distance", Double.class, CarRide::getDistance),
                Column.readonly("Category", Category.class, CarRide::getCategory)
        ));
    }
}
