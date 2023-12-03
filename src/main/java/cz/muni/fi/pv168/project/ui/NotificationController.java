package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.notification.NotificationEvent;
import cz.muni.fi.pv168.project.ui.notification.NotificationPanel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class NotificationController {
    private final static int TIME_LIMIT = 50;
    private final NotificationPanel notificationPanel = new NotificationPanel();
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final Window window;
    private ScheduledFuture currentNotification;

    public NotificationController(Window window) {
        this.window = window;
        this.window.add(notificationPanel);
        window.addWindowStateListener((a) -> SwingUtilities.invokeLater(this::refreshPosition));
        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(NotificationController.this::refreshPosition);
            }
        });
    }

    public void showTableNotification(JTable table, TableModelEvent event) {
        updateNotification(table, event);
        if (currentNotification != null) {
            currentNotification.cancel(true);
        }
        refreshPosition();
        runNotification();
    }

    /**
     * Assigns NotificationEvent to panel.
     * Handles timing, since some events need to be processed by the {@link TableModel} first
     */
    private void updateNotification(JTable table, TableModelEvent event) {
        if (event.getType() == TableModelEvent.DELETE) {
            notificationPanel.setNotification(new NotificationEvent(table, event));
        } else {
            SwingUtilities.invokeLater(() -> notificationPanel.setNotification(new NotificationEvent(table, event)));
        }
    }

    /**
     * Method to recalculate and reset position of notification / notifications
     * Should be called when window is resized
     */
    private void refreshPosition() {
        Point location = new Point(
                window.getSize().width - notificationPanel.getWidth() - 25,
                window.getSize().height - notificationPanel.getHeight() - 70);
        notificationPanel.setLocation(location);
    }

    /**
     * Lifecycle of visible notification
     */
    private void runNotification() {
        notificationPanel.setVisible(true);
        currentNotification = executor.schedule(() -> {
            notificationPanel.setVisible(false);
            currentNotification = null;
        }, TIME_LIMIT, TimeUnit.SECONDS);
    }
}
