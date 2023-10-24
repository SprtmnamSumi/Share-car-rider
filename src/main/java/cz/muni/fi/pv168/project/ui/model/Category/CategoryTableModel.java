package cz.muni.fi.pv168.project.ui.model.Category;

import com.google.inject.Singleton;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.service.crud.ICategoryCrudService;
import cz.muni.fi.pv168.project.business.service.crud.ICrudService;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.inject.Inject;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
@Singleton
public class CategoryTableModel extends TableModel<Category> {
    @Inject
    public CategoryTableModel(ICategoryCrudService categoryCrudService) {
        super(categoryCrudService, List.of(
                Column.editable("Name", String.class, Category::getName, Category::setName),
                Column.editable("Colour", String.class, Category::getColour, Category::setColour)));
    }

}
