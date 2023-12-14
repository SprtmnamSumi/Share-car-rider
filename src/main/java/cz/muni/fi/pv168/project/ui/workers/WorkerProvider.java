package cz.muni.fi.pv168.project.ui.workers;

import com.google.inject.Singleton;
import cz.muni.fi.pv168.project.ui.NotificationController;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@Singleton
public class WorkerProvider {
    private final AtomicReference<AsyncWorker> blockingTask = new AtomicReference<>();
    private NotificationController notificationController;

    public void setListener(NotificationController controller) { // May be reworked to proper Observable pattern
        notificationController = controller;
    }

    public boolean submitTask(Function<Void, Boolean> doStuff, Runnable onError, String taskName) {
        AsyncWorker newTask = new AsyncWorker(doStuff, onFinishTask(taskName), onErrorTask(onError, taskName));
        if (blockingTask.compareAndSet(null, newTask)) {
            blockingTask.get().perform();
            notificationController.showIOProgressNotification(taskName + " operation is in progress");
            return true;
        }
        return false;
    }

    private Runnable onErrorTask(Runnable onError, String taskName) {
        return () -> {
            onError.run();
            blockingTask.set(null);
            notificationController.showIOProgressNotification(taskName + " failed to finish");
        };
    }

    private Runnable onFinishTask(String taskName) {
        return () -> {
            blockingTask.set(null);
            notificationController.showIOProgressNotification(taskName + " finished successfully");
        };
    }
}
