package ru.digitalspirit.asaka.bpm.util;

import org.apache.commons.lang3.builder.ToStringStyle;
import ru.digitalspirit.asaka.bpm.model.common.Describable;

import java.util.Collection;

public class Styles {

    private Styles() {
        throw new IllegalStateException("Utility class");
    }

    public static class ClassNameWithLineBreaksToStringStyle extends ToStringStyle {

        @Override
        @SuppressWarnings("unchecked")
        protected void appendDetail(StringBuffer buffer, String fieldName, Collection<?> coll) {
            if (coll == null || coll.isEmpty() || !(coll.iterator().next() instanceof Describable)) {
                super.appendDetail(buffer, fieldName, coll);
            } else {
                Collection<Describable> describables = (Collection<Describable>) coll;
                for (Describable describable : describables) {
                    appendDetail(buffer, fieldName, describable);
                }
            }
        }
    }

    public static class ShortClassNameWithLineBreakToStringStyle extends ClassNameWithLineBreaksToStringStyle {

        public ShortClassNameWithLineBreakToStringStyle() {
            super();
            setUseShortClassName(true);
            setUseIdentityHashCode(false);
            setFieldSeparatorAtStart(true);

            setNullText(Constants.NULL_STRING);
            setContentStart(Constants.SPACE + Constants.OPEN_BRACKET);
            setFieldSeparator(Constants.LINE_SEPARATOR + Constants.TAB);
            setFieldNameValueSeparator(Constants.SPACE + Constants.EQUALS + Constants.SPACE);
            setContentEnd(Constants.LINE_SEPARATOR + Constants.CLOSE_BRACKET);
        }

        @Override
        protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
            if (value instanceof Describable) {
                value = ((Describable) value).describe(getFieldSeparator() + Constants.TAB);
            } else if (value instanceof String) {
                value = ((String) value).replaceAll(Constants.NEW_LINE, Constants.SPACE);
            }
            super.appendDetail(buffer, fieldName, value);
        }

        @Override
        protected void appendClassName(StringBuffer buffer, Object object) {
            buffer.append(Constants.LINE_SEPARATOR);
            super.appendClassName(buffer, object);
        }
    }

    public static class NoClassNameWithLineBreakToStringStyle extends ClassNameWithLineBreaksToStringStyle {

        private final String linePrefix;

        public NoClassNameWithLineBreakToStringStyle(String linePrefix) {
            super();
            if (linePrefix == null) {
                throw new IllegalArgumentException("LinePrefix can't be null!");
            }
            this.linePrefix = linePrefix;

            setUseShortClassName(false);
            setUseClassName(false);
            setUseIdentityHashCode(false);
            setFieldSeparatorAtStart(false);

            setNullText(Constants.NULL_STRING);
            setContentStart(Constants.OPEN_BRACKET + linePrefix);
            setFieldSeparator(linePrefix);
            setFieldNameValueSeparator(Constants.SPACE + Constants.EQUALS + Constants.SPACE);
            setContentEnd(linePrefix.substring(0, linePrefix.length() - 1) + Constants.CLOSE_BRACKET);
        }

        @Override
        protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
            if (value instanceof Describable) {
                value = ((Describable) value).describe(linePrefix + Constants.TAB);
            } else if (value instanceof String) {
                value = ((String) value).replaceAll(Constants.NEW_LINE, Constants.SPACE);
            }

            super.appendDetail(buffer, fieldName, value);
        }
    }
}
