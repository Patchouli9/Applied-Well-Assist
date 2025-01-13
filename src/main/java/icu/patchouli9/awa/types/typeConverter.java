package icu.patchouli9.awa.types;

import java.text.NumberFormat;

import icu.patchouli9.awa.Main;

public class typeConverter {

    public static Object convertStringToType(String value, Class<?> targetType) {
        if (value == null) {
            return null;
        }
        if (targetType == String.class) {
            return value;
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.valueOf(value);
        }

        try {
            Number number = NumberFormat.getInstance()
                .parse(value);
            if (targetType == float.class || targetType == Float.class) {
                return number.floatValue();
            } else if (targetType == double.class || targetType == Double.class) {
                return number.doubleValue();
            } else if (targetType == int.class || targetType == Integer.class) {
                return number.intValue();
            } else if (targetType == long.class || targetType == Long.class) {
                return number.longValue();
            } else if (targetType == short.class || targetType == Short.class) {
                return number.shortValue();
            } else if (targetType == byte.class || targetType == Byte.class) {
                return number.byteValue();
            }
        } catch (Exception e) {
            Main.warn(e);
        }

        return value;
    }
}
