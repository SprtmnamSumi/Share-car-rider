package cz.muni.fi.pv168.project.ui.model.CarRide;

import cz.muni.fi.pv168.project.entities.CarRideTemplate;
import cz.muni.fi.pv168.project.ui.model.ListModel;

import java.util.List;

public class CarRideListModel extends ListModel<CarRideTemplate> {
    public CarRideListModel(List<CarRideTemplate> categories) {
        super(categories);
    }
}
