package cz.muni.fi.pv168.project.ui.panels.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.util.ConversionUtils;

import javax.swing.table.DefaultTableCellRenderer;

public class CategoryTableCell extends DefaultTableCellRenderer {
    public CategoryTableCell(Category category) {
        super();
        if (category != null) {
            this.setText(category.getName());
            this.setBackground(ConversionUtils.getDimColor(category.getColour()));
        }
    }
}
