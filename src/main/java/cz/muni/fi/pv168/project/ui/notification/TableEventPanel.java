package cz.muni.fi.pv168.project.ui.notification;

import cz.muni.fi.pv168.project.ui.icons.CachedIconLoader;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableEventPanel extends AbstractNotificationPanel {
    private final JLabel message = new JLabel();
    private NotificationEvent notification;

    public TableEventPanel() {
        super();
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
        this.revalidate();
        this.repaint();
    }

    private JButton getCloseButton() {
        JButton button = new JButton();
        button.addActionListener((a) -> this.setVisible(false));
        button.setPreferredSize(new Dimension(24, 24));
        button.setIcon(new CachedIconLoader().getIcon("cross.png", 24, 24));
        return button;
    }
}
