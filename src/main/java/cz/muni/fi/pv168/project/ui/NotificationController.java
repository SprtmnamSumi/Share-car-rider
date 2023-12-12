package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.notification.AbstractNotificationPanel;
import cz.muni.fi.pv168.project.ui.notification.NotificationEvent;
import cz.muni.fi.pv168.project.ui.notification.TableEventPanel;
import cz.muni.fi.pv168.project.ui.notification.IOEventPanel;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class NotificationController {
    private final static int TIME_LIMIT = 5;
    private final TableEventPanel tableEventPanel = new TableEventPanel();
    private final IOEventPanel ioEventPanel = new IOEventPanel();
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final Window window;
    private final AtomicReference<AbstractNotificationPanel> currentNotification = new AtomicReference<>();
    private ScheduledFuture currentFuture;

    public NotificationController(Window window) {
        this.window = window;
        this.window.add(tableEventPanel);
        this.window.add(ioEventPanel);
        window.addWindowStateListener((a) -> SwingUtilities.invokeLater(this::refreshPosition));
        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(NotificationController.this::refreshPosition);
            }
        });
    }

    public void showIOProgressNotification(String message){
        ioEventPanel.setMessage(message);
        showNotification(ioEventPanel);
    }

    public void showTableNotification(JTable table, TableModelEvent event) {
        if(!ioEventPanel.isShowing()){
            updateNotification(table, event);
            showNotification(tableEventPanel);
        }
    }

    /**
     * Assigns NotificationEvent to panel.
     * Handles timing, since some events need to be processed by the {@link TableModel} first
     */
    private void updateNotification(JTable table, TableModelEvent event) {
        if (event.getType() == TableModelEvent.DELETE) {
            tableEventPanel.setNotification(new NotificationEvent(table, event));
        } else {
            SwingUtilities.invokeLater(() -> tableEventPanel.setNotification(new NotificationEvent(table, event)));
        }
    }

    /**
     * Method to recalculate and reset position of notification / notifications
     * Should be called when window is resized
     */
    private void refreshPosition() {
        Point location = new Point(
                window.getSize().width - ioEventPanel.getWidth() - 25,
                window.getSize().height - ioEventPanel.getHeight() - 70);
        ioEventPanel.setLocation(location);
        tableEventPanel.setLocation(location);
    }

    /**
     * Lifecycle of visible notification
     */
    private void showNotification(AbstractNotificationPanel notification) {
        if (currentNotification.get() != null && !currentFuture.isCancelled()) {
            currentFuture.cancel(true);
        }
        refreshPosition();
        notification.setVisible(true);
        currentFuture = executor.schedule(() -> {
            notification.setVisible(false);
            currentNotification.set(null);
        }, TIME_LIMIT, TimeUnit.SECONDS);
    }
}
