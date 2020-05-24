package com.lamzone.mareunion.utils;

public class DateUtils {


    public static String timePickerSet(int hourOfDay, int minute) {
        String timeDialogBox = "";

        if (minute < 10 && hourOfDay < 10) {
            timeDialogBox = "0" + hourOfDay + "H0" + minute;
        } else if (minute < 10) {
            timeDialogBox = hourOfDay + "H0" + minute;
        } else if (hourOfDay < 10) {
            timeDialogBox = "0" + hourOfDay + "H" + minute;
        } else {
            timeDialogBox = hourOfDay + "H" + minute;
        }
        return timeDialogBox;
    }


    public static String datePickerSet(int year, int month, int dayOfMonth) {
        String enterDate = "";

        if (month < 10 && dayOfMonth < 10) {
            enterDate = "0" + dayOfMonth + "/0" + month + "/" + year;
        } else if (month < 10) {
            enterDate = dayOfMonth + "/0" + month + "/" + year;
        } else if (dayOfMonth < 10) {
            enterDate = "0" + dayOfMonth + "/" + month + "/" + year;
        } else {
            enterDate = dayOfMonth + "/" + month + "/" + year;
        }
        return enterDate;
    }


}
