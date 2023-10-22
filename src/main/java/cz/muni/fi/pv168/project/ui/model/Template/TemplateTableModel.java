package cz.muni.fi.pv168.project.ui.model.Template;

import cz.muni.fi.pv168.project.bussiness.model.Category;
import cz.muni.fi.pv168.project.bussiness.model.Template;
import cz.muni.fi.pv168.project.bussiness.service.crud.ICrudService;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link Template} objects.
 */
public class TemplateTableModel extends TableModel<Template> {


    public TemplateTableModel(ICrudService<Template> crudService) {
        super(crudService, List.of(
                Column.editable("Name", String.class, Template::getTitle, Template::setTitle),
                Column.editable("Distance", Double.class, Template::getDistance, Template::setDistance),
                Column.editable("Category", Category.class, Template::getCategory, Template::setCategory)
        ));

    }
}
