package com.lamzone.mareunion.model.services;

import com.lamzone.mareunion.model.items.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingService implements ApiMeeting {

    private List<Meeting> mMeetings = new ArrayList<>();

   /*
   necessite de penser a l'archi pour plus tard: un tableau vide ne sera pas a remplacer alors qu'une ligne de code ciblee si dans le cas ou l'api change
    */

    @Override
    public List<Meeting> getMeeting() {
        return mMeetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        if (meeting == null) {
            throw new IllegalArgumentException("Meeting may not be null");
        }
        mMeetings.remove(meeting);
    }

    @Override
    public void addNewMeeting(Meeting meeting) {
        if (meeting == null) {
            throw new IllegalArgumentException("Meeting may not be null");
        }
        mMeetings.add(meeting);
    }

    @Override
    public List<Meeting> filteringOptions(String filteredOption) {
        List<Meeting> mMeetingFiltered = new ArrayList<>();
        for (Meeting meeting : mMeetings) {
            if (meeting.getMeetingDate().equals(filteredOption) || meeting.getMeetingPlaceName().equals(filteredOption))
                mMeetingFiltered.add(meeting);
        }
        return mMeetingFiltered;
    }


}
