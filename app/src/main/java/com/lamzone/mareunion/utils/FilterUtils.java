package com.lamzone.mareunion.utils;

import com.lamzone.mareunion.di.DI;
import com.lamzone.mareunion.model.items.Meeting;
import com.lamzone.mareunion.model.services.ApiMeeting;

import java.util.ArrayList;
import java.util.List;

public class FilterUtils {

    public static List<Meeting> filteringOptions(String filteredOption){
        ApiMeeting mApiMeeting = DI.getMeetingApi();
        List<Meeting> mMeetings = new ArrayList<>();
        List<Meeting> mMeetingFiltered = mApiMeeting.getMeeting();
        for (Meeting meeting : mMeetings) {
            if (meeting.getMeetingDate().equals(filteredOption))
                mMeetingFiltered.add(meeting);
        }
        return mMeetingFiltered;
    }

//    public static List<Meeting> filteringTimeOptions(int hourOfDay, int minute){
//        ApiMeeting mApiMeeting = DI.getMeetingApi();
//        List<Meeting> mMeetings = new ArrayList<>();
//        List<Meeting> mMeetingFiltered = mApiMeeting.getMeeting();
//        for (Meeting meeting : mMeetings) {
//            if (meeting.getMeetingDate().equals(DateUtils.timePickerSet(hourOfDay, minute)))
//                mMeetingFiltered.add(meeting);
//        }
//        return mMeetingFiltered;
//    }


}
