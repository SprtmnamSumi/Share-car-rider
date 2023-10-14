package cz.muni.fi.pv168.project.ui.model.templates;

import cz.muni.fi.pv168.project.entities.CarRideTemplate;
import cz.muni.fi.pv168.project.ui.model.ListModel;

import java.util.List;

public class TemplateListModel extends ListModel<CarRideTemplate> {
    public TemplateListModel(List<CarRideTemplate> categories) {
        super(categories);
    }

}
