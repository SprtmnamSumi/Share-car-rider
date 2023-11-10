package cz.muni.fi.pv168.project.ui.model.Template;

import com.google.inject.Singleton;
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
@Singleton
public class TemplateTableModel extends TableModel<Template> {

    @Inject
    public TemplateTableModel(ICarRideTempalteService crudService) {
        super(crudService, List.of(
                Column.readonly("Name", String.class, Template::getTitle),
                Column.readonly("Distance", Double.class, Template::getDistance),
                Column.readonly("Category", Category.class, Template::getCategory)
        ));
    }
}
