package ru.digitalspirit.asaka.util;

import java.util.Arrays;
import java.util.List;

public class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String getDelimitedString(String delimiter, String... strs) {
        return getDelimitedString(delimiter, Arrays.asList(strs));
    }

    public static String getDelimitedString(String delimiter, List<String> stringList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < stringList.size(); i++) {
            if (stringList.get(i) != null) {
                stringBuffer.append(stringList.get(i));
                if (i < stringList.size() - 1) {
                    stringBuffer.append(delimiter);
                }
            }
        }
        return stringBuffer.toString();
    }

}
