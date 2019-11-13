package com.parkson.assignment.exception;

public final class UnProcessAbleEntity extends RuntimeException {
    private static final long serialVersionUID = 5861310537366287163L;

    public UnProcessAbleEntity() {
        super();
    }

    public UnProcessAbleEntity(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnProcessAbleEntity(final String message) {
        super(message);
    }

    public UnProcessAbleEntity(final Throwable cause) {
        super(cause);
    }
}