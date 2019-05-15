package daoLayer.services.exceptions;

public class LedgerServiceException extends Exception{

    String message;

    public LedgerServiceException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
