package cz.muni.fi.pv168.project.ui.model.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.model.ListModel;

import java.util.List;

public class CategoryListModel extends ListModel<Category> {
    public CategoryListModel(List<Category> categories) {
        super(categories);
    }

}
