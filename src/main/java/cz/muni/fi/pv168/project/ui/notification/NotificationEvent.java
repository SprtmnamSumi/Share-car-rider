package cz.muni.fi.pv168.project.ui.notification;

import cz.muni.fi.pv168.project.ui.model.table.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.table.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.table.CurrencyTableModel;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.model.table.TemplateTableModel;

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
        var index = table.getRowSorter().convertRowIndexToView(event.getFirstRow());
        this.viewIndex = Math.max(index, 0);
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
        String item = getTypeName();
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

    private String getTypeName() {
        if (source instanceof CarRideTableModel) {
            return "CarRide";
        }
        if (source instanceof CategoryTableModel) {
            return "Category";
        }
        if (source instanceof TemplateTableModel) {
            return "Template";
        }
        if (source instanceof CurrencyTableModel) {
            return "Currency";
        }
        return "Unknown";
    }

    public enum NotificationType {
        ADD, UPDATE, DELETE
    }
}
