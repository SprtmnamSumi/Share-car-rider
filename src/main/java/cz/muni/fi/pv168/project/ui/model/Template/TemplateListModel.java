package cz.muni.fi.pv168.project.ui.model.Template;

import cz.muni.fi.pv168.project.entities.Template;
import cz.muni.fi.pv168.project.ui.model.ListModel;

import java.util.List;

public class TemplateListModel extends ListModel<Template> {
    public TemplateListModel(List<Template> categories) {
        super(categories);
    }
}
