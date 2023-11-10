package cz.muni.fi.pv168.project.ui.model.CarRide;

import com.google.inject.Singleton;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.service.crud.ICarRideICrudService;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
@Singleton
public class CarRideTableModel extends TableModel<CarRide> {

    @Inject
    public CarRideTableModel(ICarRideICrudService crudService) {
        super(crudService, List.of(
                Column.readonly("Date", LocalDateTime.class, CarRide::getDate),
                Column.readonly("Name", String.class, CarRide::getTitle),
                Column.readonly("Distance", Double.class, CarRide::getDistance),
                Column.readonly("Category", Category.class, CarRide::getCategory)
        ));
    }
}
