package org.bibalex.eol.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date =sdf.format(new Date());
        return date;
    }
}
