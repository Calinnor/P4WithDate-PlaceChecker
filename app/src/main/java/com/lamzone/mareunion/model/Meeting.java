package com.lamzone.mareunion.model;

public class Meeting {

    private int meetingColorTag;
    private String meetingSubject;
    private String meetingHour;
    private String meetingPlaceName;
    private String meetingParticipantsInformations;
    private String meetingDate;

    public Meeting(int meetingColorTag, String meetingSubject, String meetingHour, String meetingPlaceName, String meetingParticipantsInformations, String meetingDate) {
        this.meetingColorTag = meetingColorTag;
        this.meetingSubject = meetingSubject;
        this.meetingHour = meetingHour;
        this.meetingPlaceName = meetingPlaceName;
        this.meetingParticipantsInformations = meetingParticipantsInformations;
        this.meetingDate = meetingDate;
    }

    public int getMeetingColorTag() {
        return meetingColorTag;
    }

    public String getMeetingSubject() {
        return meetingSubject;
    }

    public String getMeetingHour() {
        return meetingHour;
    }

    public String getMeetingPlaceName() {
        return meetingPlaceName;
    }

    public String getMeetingParticipantsInformations() {
        return meetingParticipantsInformations;
    }

    public String getMeetingDate() {
        return meetingDate;
    }
}
