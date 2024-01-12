package cz.muni.fi.pv168.project.ui.notification;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

public abstract class AbstractNotificationPanel extends JInternalFrame {
    private final static Dimension size = new Dimension(250, 40);

    protected final JPanel panel = getGradientJPanel();
    AbstractNotificationPanel(){
        setLookAndFeel();
    }
    private void setLookAndFeel() {
        this.setBorder(BorderFactory.createSoftBevelBorder(0));
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setResizable(false);
        this.setSize(size);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
    }

    private JPanel getGradientJPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D g2d) {
                    Paint p = new GradientPaint(0.0f, 0.0f, new Color(150, 200, 255, 10),
                            0.0f, getHeight(), new Color(150, 200, 255, 80), true);
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setLayout(new FlowLayout());
        return panel;
    }
}
