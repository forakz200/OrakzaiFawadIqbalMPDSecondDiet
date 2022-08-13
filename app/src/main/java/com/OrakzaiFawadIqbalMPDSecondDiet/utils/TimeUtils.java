//Fawad Iqbal Orakzai S2027118
package com.OrakzaiFawadIqbalMPDSecondDiet.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    public static String convertDateToShowDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        Date date1;
        try {
            date1 = simpleDateFormat.parse(date);
            if (date1 == null) {
                return date;
            } else {
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.US);
                return simpleDateFormat1.format(date1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getForecastDate(String date, int dayIncrement, boolean removeDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.US);
        Date date1;
        try {
            date1 = simpleDateFormat.parse(date);
            if (date1 == null) {
                return date;
            } else {
                Calendar tempCal = Calendar.getInstance();
                tempCal.setTime(date1);
                tempCal.add(Calendar.DAY_OF_MONTH, dayIncrement);
                SimpleDateFormat simpleDateFormat1;
                if (!removeDay)
                    simpleDateFormat1 = new SimpleDateFormat("EEEE, dd MMM yyyy", Locale.US);
                else
                    simpleDateFormat1 = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
                return simpleDateFormat1.format(tempCal.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Calendar getTimeForAlarm(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        Date date1;
        try {
            date1 = simpleDateFormat.parse(time);
            if (date1 == null) {
                return Calendar.getInstance();
            } else {
                Calendar tempCal = Calendar.getInstance();
                tempCal.setTime(date1);
                return tempCal;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Calendar.getInstance();
    }

    public String getTimeForAlarm(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        return simpleDateFormat.format(calendar.getTime());
    }


    public static String convertTimeto12hr(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.US);
        Date date1;
        try {
            date1 = simpleDateFormat.parse(date.trim());
            if (date1 == null) {
                return date;
            } else {
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm a", Locale.US);
                return simpleDateFormat1.format(date1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}
