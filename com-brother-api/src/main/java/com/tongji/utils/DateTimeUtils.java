package com.tongji.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 13248 on 2016/11/12.
 */
public class DateTimeUtils {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getNowDateTime() {
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}
