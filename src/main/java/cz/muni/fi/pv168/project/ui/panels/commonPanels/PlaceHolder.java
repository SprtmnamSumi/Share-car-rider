package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class PlaceHolder extends JTextField {
    private String placeholder;

    public PlaceHolder() {
    }

    public PlaceHolder(final String pText) {
        super(pText);
    }




    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }

}
