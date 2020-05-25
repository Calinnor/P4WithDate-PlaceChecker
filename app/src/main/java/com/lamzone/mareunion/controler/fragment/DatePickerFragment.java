package com.lamzone.mareunion.controler.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Objects;

public class DatePickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        /**
         * ligne under return a calendar since year 1900. It was my first implementation
         */
        //return new DatePickerDialog(Objects.requireNonNull(getActivity()), (DatePickerDialog.OnDateSetListener) getActivity(), day, month, year);

        /**
         * here i enter a var which contains the global calendar
         */
        DatePickerDialog dialogDate = new DatePickerDialog(Objects.requireNonNull(getActivity()), (DatePickerDialog.OnDateSetListener) getActivity(), day, month, year);

        /**
         * here i enter the methode minDate with currentmillis in param (today all the time) thks to alph who show me the way
         */
        dialogDate.getDatePicker().setMinDate(System.currentTimeMillis());
        return dialogDate;
    }
}
