package com.lamzone.mareunion.fakeServices;

import com.lamzone.mareunion.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class FakeMeetingService implements FakeApiMeeting {

    //private List<Meeting> mMeetings = FakeMeetingGenerator.generateFakeMeeting();
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
        mMeetings.remove(meeting);
    }

    @Override
    public void addNewMeeting(Meeting meeting) {
        mMeetings.add(meeting);

    }
}
