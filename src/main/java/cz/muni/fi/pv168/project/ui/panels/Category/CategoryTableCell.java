package cz.muni.fi.pv168.project.ui.panels.Category;

import cz.muni.fi.pv168.project.business.model.Category;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CategoryTableCell extends DefaultTableCellRenderer {
    public CategoryTableCell(Category category) {
        super();

        if (category != null) {
            this.setText(category.getName());
            this.setBackground(new Color(category.getColour()));
        }

    }
}
