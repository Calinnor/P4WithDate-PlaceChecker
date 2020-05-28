package com.lamzone.mareunion.model.items;

public class Meeting {

    private int meetingColorTag;
    private String meetingSubject;
    private String meetingStartHour;
    private String meetingEndHour;
    private String meetingPlaceName;
    private String meetingParticipantsInformations;
    private String meetingDate;
    private long meetingDateDisponibility;

    public Meeting(int meetingColorTag, String meetingSubject, String meetingStartHour, String meetingEndHour, String meetingPlaceName, String meetingParticipantsInformations, String meetingDate, long meetingDateDisponibility) {
        this.meetingColorTag = meetingColorTag;
        this.meetingSubject = meetingSubject;
        this.meetingStartHour = meetingStartHour;
        this.meetingEndHour = meetingEndHour;
        this.meetingPlaceName = meetingPlaceName;
        this.meetingParticipantsInformations = meetingParticipantsInformations;
        this.meetingDate = meetingDate;
        this.meetingDateDisponibility = meetingDateDisponibility;
    }

    public int getMeetingColorTag() {
        return meetingColorTag;
    }

    public String getMeetingSubject() {
        return meetingSubject;
    }

    public String getMeetingStartHour() {
        return meetingStartHour;
    }

    public String getMeetingEndHour() {
        return meetingEndHour;
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

    public long getMeetingDateDisponibility() { return meetingDateDisponibility; }
}
