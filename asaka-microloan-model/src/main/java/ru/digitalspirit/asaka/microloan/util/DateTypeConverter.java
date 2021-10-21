package ru.digitalspirit.asaka.microloan.util;


import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public final class DateTypeConverter {

    private DateTypeConverter() {
    }

    public static Date parseDate(String s) {
      /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (s == null) {
            return null;
        }
        return sdf.format(DatatypeConverter.parseDate(s).getTime());*/
        if (s == null || s == "") {
            return null;
        }
        return Date.valueOf(s);
    }

    public static String printDate(Date date) {
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (s == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(s));
        } catch (ParseException e) {
            return null;
        }
        return DatatypeConverter.printDate(c);*/
        if (date == null) {
            return null;
        }
        return date.toString();
    }

    public static Time parseTime(String s) {
        /*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ssX");
        if (s == null) {
            return null;
        }
        return sdf.format(DatatypeConverter.parseTime(s).getTime());*/
        if (s == null || s == "") {
            return null;
        }
        return Time.valueOf(s);
    }
    public static String printTime(Time time) {
       /* SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ssX");
        if (s == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(s));
        } catch (ParseException e) {
            return null;
        }
        return DatatypeConverter.printTime(c);*/
        if (time == null) {
            return null;
        }
        return time.toString();
    }

    public static Timestamp parseDateTime(String s) {
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        if (s == null) {
            return null;
        }
        return sdf.format(DatatypeConverter.parseDateTime(s).getTime());*/
        if (s == null || s == "") {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        try {
            return new Timestamp(sdf.parse(s).getTime());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public static String printDateTime(Timestamp ts) {
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        if (s == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(s));
        } catch (ParseException e) {
            return null;
        }
        return DatatypeConverter.printDateTime(c);*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        if (ts == null) {
            return null;
        }
        return sdf.format(new Date(ts.getTime()));
    }

}
