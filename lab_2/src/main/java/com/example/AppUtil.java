package com.example;

import org.json.JSONObject;

import java.sql.Date;
import java.time.LocalDate;

public class AppUtil {

    public static LocalDate toLocalDate(Date date) {
        if (date == null) return null;

        return date.toLocalDate();
    }

    public static Date toSQLDate(LocalDate date) {
        if (date == null) return null;

        return Date.valueOf(date);
    }

    public static LocalDate toLocalDate(Object date) {
        if (date == JSONObject.NULL) return null;

        String dateS = date.toString();
        int year = Integer.parseInt(dateS.substring(0, 4));
        int month = Integer.parseInt(dateS.substring(5, 7));
        int day = Integer.parseInt(dateS.substring(8));

        return LocalDate.of(year, month, day);
    }

}
