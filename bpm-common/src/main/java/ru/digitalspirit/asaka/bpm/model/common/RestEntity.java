package ru.digitalspirit.asaka.bpm.model.common;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import ru.digitalspirit.asaka.bpm.util.Styles;

public class RestEntity implements Describable {

    @Override
    public String describe() {
        return new ReflectionToStringBuilder(this, new Styles.ShortClassNameWithLineBreakToStringStyle()).build();
    }

    @Override
    public String describe(String linePrefix) {
        return new ReflectionToStringBuilder(this, new Styles.NoClassNameWithLineBreakToStringStyle(linePrefix)).build();
    }
}
