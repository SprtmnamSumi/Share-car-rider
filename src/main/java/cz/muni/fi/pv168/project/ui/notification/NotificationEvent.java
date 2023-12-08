package cz.muni.fi.pv168.project.ui.notification;

import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import java.awt.Rectangle;
import java.time.Instant;

public class NotificationEvent {
    private final NotificationType type;
    private final TableModel source;
    private final JTable table;
    private final long timestamp = Instant.now().toEpochMilli();
    private final int viewIndex;
    private int numberOfItems;

    public NotificationEvent(JTable table, TableModelEvent event) {
        this.table = table;
        this.type = getNotificationtype(event);
        this.source = (TableModel) event.getSource();
        this.viewIndex = table.getRowSorter().convertRowIndexToView(event.getFirstRow());
        numberOfItems = 1;
    }

    public void focusOnChange() {
        table.clearSelection();
        table.getSelectionModel().addSelectionInterval(viewIndex, viewIndex);
        table.scrollRectToVisible(new Rectangle(table.getCellRect(viewIndex, 0, true)));
    }

    public void addItems(int number) {
        numberOfItems += number;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public NotificationType getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        String item = "";
        if (source instanceof CarRideTableModel) {
            item = "CarRide";
        } else {
            if (source instanceof CategoryTableModel) {
                item = "Category";
            } else {
                if (source instanceof TemplateTableModel) {
                    item = "Template";
                }
            }
        }
        return switch (type) {
            case ADD -> numberOfItems < 2
                    ? String.format("New %s was added on %d row", item, viewIndex)
                    : String.format("%d new %s items were added", numberOfItems, item);
            case UPDATE -> numberOfItems < 2
                    ? String.format("%s was updated on %d row", item, viewIndex)
                    : String.format("%d items of %s were updated", numberOfItems, item);
            case DELETE -> numberOfItems < 2
                    ? String.format("%s was deleted on %d row", item, viewIndex)
                    : String.format("%d items of %s were deleted", numberOfItems, item);
        };
    }

    private NotificationEvent.NotificationType getNotificationtype(TableModelEvent event) {
        return switch (event.getType()) {
            case TableModelEvent.DELETE -> NotificationEvent.NotificationType.DELETE;
            case TableModelEvent.INSERT -> NotificationEvent.NotificationType.ADD;
            case TableModelEvent.UPDATE -> NotificationEvent.NotificationType.UPDATE;
            default -> throw new IllegalStateException("Unexpected value: " + event.getType());
        };
    }

    public enum NotificationType {
        ADD, UPDATE, DELETE
    }
}
