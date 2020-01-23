package com.fh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String format(Date date){
        return SDF.format(date);
    }

}
