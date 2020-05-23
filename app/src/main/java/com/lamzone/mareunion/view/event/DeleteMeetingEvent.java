package com.lamzone.mareunion.view.event;

import com.lamzone.mareunion.model.items.Meeting;

/**
 * delete event
 * 1/create class event call in recycler
 */

public class DeleteMeetingEvent {

    public Meeting mMeeting;

    /**
     * Constructor.
     *
     * @param meeting
     */
    public DeleteMeetingEvent(Meeting meeting) {
        this.mMeeting = meeting;
    }
}
