package cz.muni.fi.pv168.project.ui.model.Category;

import com.google.inject.Singleton;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.service.crud.ICrudService;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.inject.Inject;
import java.util.*;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
@Singleton
public class CategoryTableModel extends TableModel<Category> {
    @Inject
    CategoryTableModel(ICrudService<Category> categoryCrudService) {
        super(categoryCrudService, List.of(

                Column.readonly("Name", String.class, Category::getName),
                Column.readonly("Colour", Integer.class, Category::getColour)));

    }

}
