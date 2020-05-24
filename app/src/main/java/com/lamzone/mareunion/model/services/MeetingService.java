package com.lamzone.mareunion.model.services;

import com.lamzone.mareunion.model.items.Meeting;
import com.lamzone.mareunion.utils.FilterUtils;

import java.util.ArrayList;
import java.util.List;

public class MeetingService implements ApiMeeting {

    private static List<Meeting> mMeetings = new ArrayList<>();

    @Override
    public List<Meeting> getMeeting() {
        return mMeetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    @Override
    public void addNewMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @Override
    public List<Meeting> filterDate(String mDateToFilter) {
        return FilterUtils.filteringOptions(mDateToFilter);
    }

    @Override
    public List<Meeting> filterPlaceName(String placeItem) {
        return FilterUtils.filteringOptions(placeItem);
    }


}
