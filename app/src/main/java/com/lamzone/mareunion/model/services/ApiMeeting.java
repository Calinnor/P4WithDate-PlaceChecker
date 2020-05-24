package com.lamzone.mareunion.model.services;

import com.lamzone.mareunion.model.items.Meeting;

import java.util.List;

public interface ApiMeeting {

    List<Meeting> getMeeting();

    void deleteMeeting(Meeting meeting);

    void addNewMeeting(Meeting meeting);

    List<Meeting> filterDate(String date);

    List<Meeting> filterPlaceName(String placeItem);

}

