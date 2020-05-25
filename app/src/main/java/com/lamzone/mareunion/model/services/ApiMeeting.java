package com.lamzone.mareunion.model.services;

import com.lamzone.mareunion.model.items.Meeting;

import java.util.List;

public interface ApiMeeting {

    List<Meeting> getMeeting();

    /**
     * @param meeting dont forget to put param for methods !(
     */

    void deleteMeeting(Meeting meeting);

    void addNewMeeting(Meeting meeting);

    List<Meeting> filteringOptions(String filteredOption);

}

