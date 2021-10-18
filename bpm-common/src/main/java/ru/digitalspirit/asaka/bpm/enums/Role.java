package ru.digitalspirit.asaka.bpm.enums;

import lombok.Getter;

public enum Role {
    KM("KM"),
    KMPBU("KMPBU"),
    MO("MO"),
    PM("PM"),
    CONT_PBU("CONT_PBU"),
    MNGR_PBU("MNGR_PBU"),
    KM_CHIEF("KM_CHIEF"),
    KMPBU_CHIEF("KMPBU_CHIEF"),
    MO_CHIEF("MO_CHIEF"),
    CONT_PBU_CHIEF("CONT_PBU_CHIEF"),
    MNGR_PBU_CHIEF("MNGR_PBU_CHIEF");

    @Getter
    private final String code;

    Role(String code) {
        this.code = code;
    }
}
