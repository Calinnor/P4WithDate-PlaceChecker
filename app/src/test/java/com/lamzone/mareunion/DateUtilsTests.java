package com.lamzone.mareunion;

import com.lamzone.mareunion.utils.DateUtils;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class DateUtilsTests {

    @Test
    public void timePickerSetModifyTimeDisplayWithSucces() {
        String timeDialogBox = DateUtils.timePickerSet(5, 8);
        assertTrue(timeDialogBox.contains("05H08"));
        timeDialogBox = DateUtils.timePickerSet(15, 8);
        assertTrue(timeDialogBox.contains("15H08"));
        timeDialogBox = DateUtils.timePickerSet(5, 18);
        assertTrue(timeDialogBox.contains("05H18"));
        timeDialogBox = DateUtils.timePickerSet(15, 18);
        assertTrue(timeDialogBox.contains("15H18"));
    }

    @Test
    public void datePickerSetModifyDateDisplayWithSucces() {
        String enterDate = DateUtils.datePickerSet(20, 5, 6);
        assertTrue(enterDate.contains("06/05/20"));
        enterDate = DateUtils.datePickerSet(20, 6, 15);
        assertTrue(enterDate.contains("15/06/20"));
        enterDate = DateUtils.datePickerSet(20, 10, 15);
        assertTrue(enterDate.contains("15/10/20"));
        enterDate = DateUtils.datePickerSet(20, 10, 5);
        assertTrue(enterDate.contains("05/10/20"));
    }
}
