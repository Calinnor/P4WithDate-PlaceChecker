package com.lamzone.mareunion.view.event;

import com.lamzone.mareunion.model.items.Meeting;

public class DeleteMeetingEvent {

    public Meeting mMeeting;

    public DeleteMeetingEvent(Meeting meeting) {
        this.mMeeting = meeting;
    }
}
