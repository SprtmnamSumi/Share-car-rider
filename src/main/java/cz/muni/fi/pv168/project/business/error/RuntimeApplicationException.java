package cz.muni.fi.pv168.project.business.error;

import java.io.Serial;
import org.tinylog.Logger;

public class RuntimeApplicationException extends RuntimeException implements ApplicationException {

    @Serial
    private static final long serialVersionUID = 0L;
    private static final String DEFAULT_USER_MESSAGE = "Oops, something went wrong!";

    private final String userMessage;

    public RuntimeApplicationException(String message) {
        this(message, message);
    }

    public RuntimeApplicationException(String userMessage, String message) {
        super(message);
        Logger.error("RuntimeApplicationException occurred: " + userMessage + ", message");
        this.userMessage = userMessage;
    }

    public RuntimeApplicationException(String userMessage, String message, Throwable cause) {
        super(message, cause);
        Logger.error("RuntimeApplicationException occurred: " + userMessage + ", message" + cause.getMessage());
        this.userMessage = userMessage;
    }

    public RuntimeApplicationException(String message, Throwable cause) {
        this(message, message, cause);
    }

    public static RuntimeApplicationException withDefaultUserMessage(String message) {
        Logger.error("RuntimeApplicationException occurred: " + message);
        return new RuntimeApplicationException(DEFAULT_USER_MESSAGE, message);
    }

    public String getUserMessage() {
        return userMessage;
    }
}
