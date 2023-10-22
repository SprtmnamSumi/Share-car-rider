package cz.muni.fi.pv168.project.ui.model.Category;

import cz.muni.fi.pv168.project.bussiness.model.CarRide;
import cz.muni.fi.pv168.project.bussiness.model.Category;
import cz.muni.fi.pv168.project.bussiness.service.crud.ICrudService;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
public class CategoryTableModel extends TableModel<Category> {

    public CategoryTableModel(ICrudService<Category> categoryCrudService) {
        super(categoryCrudService, List.of(
                Column.editable("Name", String.class, Category::getName, Category::setName),
                Column.editable("Colour", String.class, Category::getColour, Category::setColour)));
    }

}
