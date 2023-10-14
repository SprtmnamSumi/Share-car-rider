//package cz.muni.fi.pv168.project.ui.renderers.OLD;
//
//import cz.muni.fi.pv168.project.ui.filters.OLDvalues.SpecialFilterGenderValues;
//import cz.muni.fi.pv168.project.ui.renderers.AbstractRenderer;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class SpecialFilterGenderValuesRenderer extends AbstractRenderer<SpecialFilterGenderValues> {
//
//    public SpecialFilterGenderValuesRenderer() {
//        super(SpecialFilterGenderValues.class);
//    }
//
//    private static void renderBoth(JLabel label) {
//        label.setText("(BOTH)");
//        label.setFont(label.getFont().deriveFont(Font.ITALIC));
//        label.setForeground(Color.GRAY);
//    }
//
//    protected void updateLabel(JLabel label, SpecialFilterGenderValues value) {
//        switch (value) {
//            case BOTH -> renderBoth(label);
//        }
//    }
//}
