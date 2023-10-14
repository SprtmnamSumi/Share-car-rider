//package cz.muni.fi.pv168.project.ui.renderers.OLD;
//
//import cz.muni.fi.pv168.project.ui.filters.OLDvalues.SpecialFilterDepartmentValues;
//import cz.muni.fi.pv168.project.ui.renderers.AbstractRenderer;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class SpecialFilterDepartmentValuesRenderer extends AbstractRenderer<SpecialFilterDepartmentValues> {
//    public SpecialFilterDepartmentValuesRenderer() {
//        super(SpecialFilterDepartmentValues.class);
//    }
//
//    private static void renderAll(JLabel label) {
//        label.setText("(ALL)");
//        label.setFont(label.getFont().deriveFont(Font.ITALIC));
//        label.setForeground(Color.GRAY);
//    }
//
//    private static void renderNoNerd(JLabel label) {
//        label.setText("NO_NERD");
//    }
//
//    protected void updateLabel(JLabel label, SpecialFilterDepartmentValues value) {
//        switch (value) {
//            case ALL -> renderAll(label);
//            case NO_NERD -> renderNoNerd(label);
//        }
//    }
//}
