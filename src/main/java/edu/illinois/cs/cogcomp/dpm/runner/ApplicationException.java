package edu.illinois.cs.cogcomp.dpm.runner;

public class ApplicationException extends Exception {

    private Exception innerException;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Exception innerException) {
        super(message);
        this.innerException = innerException;
    }

    public Exception getInnerException() {
        return innerException;
    }

    public void setInnerException(Exception innerException) {
        this.innerException = innerException;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " " + innerException.getMessage();
    }
}
