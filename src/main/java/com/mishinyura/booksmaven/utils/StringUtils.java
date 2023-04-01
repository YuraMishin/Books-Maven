package com.mishinyura.booksmaven.utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Class StringUtils.
 * Implements string utils.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 28.03.2023
 */
public final class StringUtils {

    private StringUtils() {
    }

    /**
     * Method fills the template with values.
     *
     * @param template Template
     * @param values   Values
     * @return String
     */
    public static String format(final String template, final Map<String, String> values) {
        var formatter = new StringBuilder(template);
        var valueList = new ArrayList<String>();
        var matcher = Pattern.compile("\\$\\{(\\w+)}").matcher(template);
        while (matcher.find()) {
            var key = matcher.group(1);
            var formatKey = String.format("${%s}", key);
            var index = formatter.indexOf(formatKey);
            if (index != -1) {
                formatter.replace(index, index + formatKey.length(), "%s");
                valueList.add(values.get(key));
            }
        }
        return String.format(formatter.toString(), valueList.toArray());
    }
}
