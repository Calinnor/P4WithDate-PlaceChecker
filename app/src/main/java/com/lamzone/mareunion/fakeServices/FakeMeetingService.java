package com.lamzone.mareunion.fakeServices;

import com.lamzone.mareunion.model.Meeting;

import java.util.List;

public class FakeMeetingService implements FakeApiMeeting {

    private List<Meeting> mMeetings= FakeMeetingGenerator.generateFakeMeeting();

    public void setmMeetings(List<Meeting> mMeetings) {
        this.mMeetings = mMeetings;
    }

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
