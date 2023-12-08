package cz.muni.fi.pv168.project.ui.renderers;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.util.ConversionUtils;

import javax.swing.JLabel;

public class CategoryRenderer extends AbstractRenderer<Category> {

    public CategoryRenderer() {
        super(Category.class);
    }

    @Override
    protected void updateLabel(JLabel label, Category value) {
        label.setText(String.format("%s %s", value.getName(), value.getColour()));
        label.setBackground(ConversionUtils.getDimColor(value.getColour()));
    }
}
