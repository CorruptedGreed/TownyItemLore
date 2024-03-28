package net.earthmc.townyitemlore.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd]");
    static Date currentDate = new Date();
    public static Object formattedDate = dateFormat.format(currentDate);

}
