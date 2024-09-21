package org.example.exception;

public class MaximumCapacityExceededException extends RuntimeException {
    public MaximumCapacityExceededException(String message) {
        super(message);
    }

    public MaximumCapacityExceededException(Throwable cause) {
        super(cause);
    }

    public MaximumCapacityExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}
