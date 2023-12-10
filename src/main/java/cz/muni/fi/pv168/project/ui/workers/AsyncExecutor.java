package cz.muni.fi.pv168.project.ui.workers;

import java.util.function.Function;
import javax.swing.SwingWorker;
import org.tinylog.Logger;

/**
 * Implementation of asynchronous exporter for UI.
 */
public class AsyncExecutor<L, T> {

    private final Function<L, Boolean> doStuff;
    private final Runnable onFinish;
    private final Runnable onError;

    public AsyncExecutor(Function<L, Boolean> doStuf, Runnable onFinish, Runnable onError) {
        this.doStuff = doStuf;
        this.onFinish = onFinish;
        this.onError = onError;
    }


    public void importData() {
        var asyncWorker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() {
                var stuff = doStuff.apply(null);
                return stuff;
            }

            @Override
            protected void done() {
                super.done();
                boolean isOk = false;
                try {
                    isOk = super.get();
                } catch (Exception e) {
                    onError.run();
                }
                (isOk ? onFinish : onError).run();
            }
        };
        try {
            asyncWorker.execute();
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error("panda");
        }

    }

}
