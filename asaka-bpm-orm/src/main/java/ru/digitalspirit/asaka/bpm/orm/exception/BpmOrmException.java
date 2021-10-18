package ru.digitalspirit.asaka.bpm.orm.exception;

public class BpmOrmException extends RuntimeException {

    public BpmOrmException(String msg) {
        super(msg);
    }

    public BpmOrmException(String msg, Throwable e) {
        super(msg, e);
    }

    public BpmOrmException(Throwable e) {
        super(e);
    }

}
