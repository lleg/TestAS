package ru.digitalspirit.asaka.bpm.service;

import lombok.Getter;

@Getter
public class BpmServiceException extends RuntimeException {

    private final Code code;

    public enum Code {
        DEFAULT,
        TASK_IS_ALREADY_CLOSED
    }

    public BpmServiceException(String msg) {
        super(msg);
        this.code = Code.DEFAULT;
    }

    public BpmServiceException(Code code, String msg) {
        super(msg);
        this.code = code;
    }

    public BpmServiceException(String msg, Object ... args) {
        super(String.format(msg, args));
        this.code = Code.DEFAULT;
    }

    public BpmServiceException(Code code, String msg, Object ... args) {
        super(String.format(msg, args));
        this.code = code;
    }

}
