package cz.muni.fi.pv168.project.ui.model.Template;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.crud.ICarRideTempalteService;
import cz.muni.fi.pv168.project.business.service.crud.ICategoryCrudService;
import cz.muni.fi.pv168.project.business.service.crud.ICrudService;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.inject.Inject;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link Template} objects.
 */
public class TemplateTableModel extends TableModel<Template> {

    @Inject
    public TemplateTableModel(ICarRideTempalteService crudService) {
        super(crudService, List.of(
                Column.editable("Name", String.class, Template::getTitle, Template::setTitle),
                Column.editable("Distance", Double.class, Template::getDistance, Template::setDistance),
                Column.editable("Category", Category.class, Template::getCategory, Template::setCategory)
        ));

    }
}
