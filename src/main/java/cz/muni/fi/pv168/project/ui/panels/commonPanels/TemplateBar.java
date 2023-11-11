package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.business.model.Template;

import javax.swing.*;
import java.awt.*;

public class TemplateBar extends JPanel {


    public TemplateBar(JComboBox<Template> categoryJComboBox, JButton addTemplate) {
        super(new FlowLayout(FlowLayout.CENTER));
        categoryJComboBox.setPreferredSize(new Dimension(400, 30));
        addTemplate.setPreferredSize(new Dimension(200, 30));
        this.add(categoryJComboBox);
        this.add(addTemplate);
    }
}
