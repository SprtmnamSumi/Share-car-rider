package cz.muni.fi.pv168.project.ui.workers;

import org.tinylog.Logger;

import javax.swing.SwingWorker;
import java.util.function.Function;

/**
 * Implementation of asynchronous exporter for UI.
 */
public class AsyncExecutor {

    private final Function<Void, Boolean> doStuff;
    private final Runnable onFinish;
    private final Runnable onError;

    public AsyncExecutor(Function<Void, Boolean> doStuff, Runnable onFinish, Runnable onError) {
        this.doStuff = doStuff;
        this.onFinish = onFinish;
        this.onError = onError;
    }


    public void perform() {
        var asyncWorker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() {
                return doStuff.apply(null);
            }

            @Override
            protected void done() {
                super.done();
                boolean isOk = false;
                try {
                    isOk = super.get() && !isCancelled();
                } catch (Exception e) {
                    Logger.error("Async task failed. Reason: " + e.getMessage());
                    onError.run();
                    return;
                }
                (isOk ? onFinish : onError).run();
            }
        };
        try {
            asyncWorker.execute();
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }

    }

}
