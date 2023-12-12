package cz.muni.fi.pv168.project.ui.notification;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.FlowLayout;

public class IOEventPanel extends AbstractNotificationPanel {
    private final JLabel message = new JLabel();
    private final JProgressBar progressBar = new JProgressBar();
    public IOEventPanel() {
        super();
        panel.add(message, FlowLayout.LEFT);
        //panel.add(progressBar, FlowLayout.CENTER);
        this.add(panel);
    }

    public void setMessage(String message){
        this.message.setText(message);
    }
}
