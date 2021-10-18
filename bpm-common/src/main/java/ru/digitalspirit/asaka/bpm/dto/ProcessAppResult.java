package ru.digitalspirit.asaka.bpm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessAppResult {

    private boolean success;
    private ErrorType error;
}
