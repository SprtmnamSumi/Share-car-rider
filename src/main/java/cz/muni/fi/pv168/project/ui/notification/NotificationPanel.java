package cz.muni.fi.pv168.project.ui.notification;

import cz.muni.fi.pv168.project.ui.icons.CachedIconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NotificationPanel extends JInternalFrame {
    private final static Dimension size = new Dimension(250, 40);
    private final JLabel message = new JLabel();
    private NotificationEvent notification;

    public NotificationPanel() {
        super();
        setLookAndFeel();
        JPanel panel = getGradientJPanel();
        panel.add(message, FlowLayout.LEFT);
        panel.add(getCloseButton(), FlowLayout.CENTER);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    notification.focusOnChange();
                }
            }
        });
        this.add(panel);
    }

    public void setNotification(NotificationEvent notification) {
        if (this.notification != null
                && this.notification.getType().equals(notification.getType())
                && notification.getTimestamp() - this.notification.getTimestamp() < 1000) {
            notification.addItems(this.notification.getNumberOfItems());
        }
        this.notification = notification;
        message.setText(notification.getMessage());
    }

    private JButton getCloseButton() {
        JButton button = new JButton();
        button.addActionListener((a) -> this.setVisible(false));
        button.setPreferredSize(new Dimension(24, 24));
        button.setIcon(new CachedIconLoader().getIcon("cross.png", 24, 24));
        return button;
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
