package Util;

import java.util.List;

public class ListUtil {
    public static void clear(List... list) {
        for (List s : list) {
            if (s != null) {
                s.clear();
            }
        }
    }
}
