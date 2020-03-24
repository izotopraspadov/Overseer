package edu.born.overseer.util;

public class ValidationUtil {

    public static boolean isNumeric(final String str) {

        // null or empty
        if (str == null || str.length() == 0)
            return false;

        return str.replaceAll("\\.", "").chars().allMatch(Character::isDigit);

    }

    public static boolean isBigDecimal(final String str) {

        if (!isNumeric(str))
            return false;
        if (!str.contains("."))
            return false;

        return true;

    }

    public static boolean isValidPhoneLength(final String str) {

        // not null
        if (str != null && str.length() == 11)
            return true;

        return false;

    }

}
