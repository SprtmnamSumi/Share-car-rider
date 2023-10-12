package cz.muni.fi.pv168.project.ui.renderers.OLD;

import cz.muni.fi.pv168.project.entities.old.Gender;
import cz.muni.fi.pv168.project.ui.renderers.AbstractRenderer;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.util.Map;

public final class GenderRenderer extends AbstractRenderer<Gender> {

    private static final Map<Gender, Icon> GENDER_ICONS = Icons.createEnumIcons(Gender.class, 16);

    public GenderRenderer() {
        super(Gender.class);
    }

    @Override
    protected void updateLabel(JLabel label, Gender gender) {
        if (gender != null) {
            label.setIcon(GENDER_ICONS.get(gender));
            label.setText(gender.toString());
        }
    }
}
