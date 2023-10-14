package cz.muni.fi.pv168.project.ui.model.templates;

import cz.muni.fi.pv168.project.entities.CarRide;
import cz.muni.fi.pv168.project.entities.CarRideTemplate;
import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import java.util.Collection;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
public class TemplateTableModel extends TableModel<CarRideTemplate> {

    public TemplateTableModel(Collection<CarRideTemplate> categories) {
        super(categories, List.of(
                Column.editable("Title", String.class, CarRideTemplate::getTitle, CarRideTemplate::setTitle),
                Column.editable("Colour", String.class, CarRideTemplate::getDescription, CarRideTemplate::setDescription)
        ));
    }

}
