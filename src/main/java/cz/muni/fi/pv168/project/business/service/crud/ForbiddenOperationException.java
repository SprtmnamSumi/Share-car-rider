package cz.muni.fi.pv168.project.business.service.crud;

public class ForbiddenOperationException extends RuntimeException {
    public ForbiddenOperationException(String message) {
        super(message);
    }
}
