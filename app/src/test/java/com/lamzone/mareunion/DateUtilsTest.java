package com.lamzone.mareunion;

import com.lamzone.mareunion.utils.DateUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DateUtilsTest {

    @Test
    public void timePickerSetModifyTimeDisplayWithSuccess() {
        String timeDialogBox = DateUtils.timePickerSet(5, 8);
        assertEquals("05H08", timeDialogBox);
        timeDialogBox = DateUtils.timePickerSet(15, 8);
        assertEquals("15H08", timeDialogBox);
        timeDialogBox = DateUtils.timePickerSet(5, 18);
        assertEquals("05H18", timeDialogBox);
        timeDialogBox = DateUtils.timePickerSet(15, 18);
        assertEquals("15H18", timeDialogBox);
    }

    @Test
    public void timePickerSetDontShowBadDisplay() {
        String timeDialogBox = DateUtils.timePickerSet(5, 8);
        assertNotEquals("5H8", timeDialogBox);
        timeDialogBox = DateUtils.timePickerSet(15, 8);
        assertNotEquals("15H8", timeDialogBox);
        timeDialogBox = DateUtils.timePickerSet(5, 18);
        assertNotEquals("5H18", timeDialogBox);
        timeDialogBox = DateUtils.timePickerSet(15, 18);
        assertNotEquals("15 H 18", timeDialogBox);
    }

    @Test
    public void datePickerSetModifyDateDisplayWithSuccess() {
        String enterDate = DateUtils.datePickerSet(20, 5, 6);
        assertEquals("06/05/20", enterDate);
        enterDate = DateUtils.datePickerSet(20, 6, 15);
        assertEquals("15/06/20", enterDate);
        enterDate = DateUtils.datePickerSet(20, 10, 15);
        assertEquals("15/10/20", enterDate);
        enterDate = DateUtils.datePickerSet(20, 10, 5);
        assertEquals("05/10/20", enterDate);
    }

    @Test
    public void datePickerSetDontShowBadDisplay() {
        String enterDate = DateUtils.datePickerSet(20, 5, 6);
        assertNotEquals("6/5/20", enterDate);
        assertNotEquals("5/6/20", enterDate);
        assertNotEquals("20/5/6", enterDate);
        assertNotEquals("20/6/5", enterDate);
        enterDate = DateUtils.datePickerSet(20, 6, 15);
        assertNotEquals("15/6/20", enterDate);
        enterDate = DateUtils.datePickerSet(20, 10, 15);
        assertNotEquals("15 / 10 / 20", enterDate);
        enterDate = DateUtils.datePickerSet(20, 10, 5);
        assertNotEquals("5/10/20", enterDate);
    }
}
