package ru.digitalspirit.asaka.bpm.orm.configuration;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class ModelConfiguration {

    private ModelConfiguration() {
        throw new IllegalStateException("Configuration class");
    }

    public static final Map<Model, String> MODEL_PACKAGE;

    static {
        Map<Model, String> modelPackage = new EnumMap<>(Model.class);
        modelPackage.put(Model.ASAKA_MICROZIME,"ru.digitalspirit.asaka.bpm.entity");
        MODEL_PACKAGE = Collections.unmodifiableMap(modelPackage);
    }
}
