package edu.born.overseer.util;

public class BuilderUtil {
    @SafeVarargs
    public static <T> boolean isNull(T... array) {
        for (T item: array) {
            if (item == null) return true;
        }
        return false;
    }
}
