package cz.muni.fi.pv168.project.ui.workers;

import com.google.inject.Singleton;
import cz.muni.fi.pv168.project.ui.NotificationController;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@Singleton
public class IOWorkerProvider {
    private final AtomicReference<AsyncExecutor> blockingTask = new AtomicReference<>();
    private NotificationController notificationController;

    public void setListener(NotificationController controller){ // May be reworked to proper Observable pattern
        notificationController = controller;
    }

    public boolean submitExport(Function<Void, Boolean> doStuff, Runnable onError) {
        AsyncExecutor newTask = new AsyncExecutor(doStuff, onFinishTask("Export finished"), onError);
        return submit(newTask, "Export started");
    }

    public boolean submitImport(Function<Void, Boolean> doStuff, Runnable onError) {
        AsyncExecutor newTask = new AsyncExecutor(doStuff, onFinishTask("Import finished"), onError);
        return submit(newTask, "Import started");
    }

    private boolean submit(AsyncExecutor newTask, String taskRunningMessage){
        if (blockingTask.compareAndSet(null, newTask)) {
            blockingTask.get().perform();
            notificationController.showIOProgressNotification(taskRunningMessage);
            return true;
        }
        return false;
    }

    private Runnable onFinishTask(String onFinishMessage) {
        return () -> {
            blockingTask.set(null);
            notificationController.showIOProgressNotification(onFinishMessage);
        };
    }
}
