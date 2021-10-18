package ru.digitalspirit.asaka.exception;

public class NbuRuntimeException extends RuntimeException {

    public NbuRuntimeException(String msg) {
        super(msg);
    }

    public NbuRuntimeException(String msg, Throwable e) {
        super(msg, e);
    }

    public NbuRuntimeException(Throwable e) {
        super(e);
    }

}
