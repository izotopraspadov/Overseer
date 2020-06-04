package edu.born.overseer.util;

public class PageUtil {

    private PageUtil() {
    }

    private static int pageLength = 5;

    public static int getFirstByPage(Integer page) {
        return page == null ? 0 : pageLength * (page - 1);
    }

    public static void setPageLength(int length) {
        pageLength = length;
    }

    public static int getPageLength() {
        return pageLength;
    }
}
