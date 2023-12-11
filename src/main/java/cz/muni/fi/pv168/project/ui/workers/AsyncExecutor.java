package cz.muni.fi.pv168.project.ui.workers;

import java.util.function.Function;
import javax.swing.SwingWorker;
import org.tinylog.Logger;

/**
 * Implementation of asynchronous exporter for UI.
 */
public class AsyncExecutor {

    private final Function<Void, Boolean> doStuff;
    private final Runnable onFinish;
    private final Runnable onError;

    public AsyncExecutor(Function<Void, Boolean> doStuf, Runnable onFinish, Runnable onError) {
        this.doStuff = doStuf;
        this.onFinish = onFinish;
        this.onError = onError;
    }


    public void importData() {
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
