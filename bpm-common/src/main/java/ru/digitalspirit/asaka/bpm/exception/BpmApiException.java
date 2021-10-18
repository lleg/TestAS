package ru.digitalspirit.asaka.bpm.exception;

public class BpmApiException extends RuntimeException {

    public BpmApiException(String msg) {
        super(msg);
    }

    public BpmApiException(String msg, Throwable e) {
        super(msg, e);
    }

    public BpmApiException(Throwable e) {
        super(e);
    }

}
