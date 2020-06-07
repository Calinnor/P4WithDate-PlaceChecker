package com.lamzone.mareunion;

import com.lamzone.mareunion.di.DI;
import com.lamzone.mareunion.model.items.Meeting;
import com.lamzone.mareunion.model.items.PlaceItem;
import com.lamzone.mareunion.model.services.LocalApiMeeting;
import com.lamzone.mareunion.model.services.LocalApiPlace;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PlaceServiceTest {

    private LocalApiPlace mLocalApiPlace;
    private LocalApiMeeting localApiMeeting;

    private List<Meeting> mMeetingPlaceFiltered = new ArrayList<>();

    Meeting meetingOne = new Meeting(R.drawable.bleu,
            "Test réunion: Objet ",
            "- 8h30 -",
            "10h00",
            "Salle 1",
            "Jack@Email - Joel@Email - Jess@Email",
            "17/05/21",
            178598654);

    @Before
    public void setupMeeting() {
        mLocalApiPlace = DI.getNewInstancePlaceApi();
        localApiMeeting = DI.getNewInstanceApi();
    }

    /**
     * PlaceItem tests
     */
    @Test
    public void getPlaceWithSuccess() {
        assertEquals(mLocalApiPlace.getPlaceItem().size(), 10);
        assertTrue(mLocalApiPlace.getPlaceItem().get(1).getmPlaceName().contains("2"));
    }

    @Test
    public void getPlaceWithNoSuccess(){
        PlaceItem expectedPlaceItem = mLocalApiPlace.getPlaceItem().get(0);
        assertNotNull(expectedPlaceItem);
        assertFalse(expectedPlaceItem.getmPlaceName().contains("3"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getPlaceItemOutOfList() {
        PlaceItem expectedPlaceItem = mLocalApiPlace.getPlaceItem().get(mLocalApiPlace.getPlaceItem().size() + 1);
        assertTrue(mLocalApiPlace.getPlaceItem().contains(expectedPlaceItem));
    }

    /**
     * PlaceName tests
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getPlaceNameOutOfList() {
        String exceptedPlaceName = mLocalApiPlace.getPlaceNames().get(12);
        assertTrue(mLocalApiPlace.getPlaceNames().contains(exceptedPlaceName));
    }

    @Test
    public void getPlaceNameWithSuccess() {
        assertTrue(mLocalApiPlace.getPlaceNames().get(3).contains("4"));
        assertTrue(mLocalApiPlace.getPlaceNames().get(2).contains("3"));
    }

    @Test
    public void getPlaceNameWithNoSuccess() {
        assertFalse(mLocalApiPlace.getPlaceNames().get(6).contains("5"));
        assertFalse(mLocalApiPlace.getPlaceNames().get(4).contains("2"));
    }

    /**
     * filter PlaceName tests
     */
    @Test
    public void filterPlaceNameWithSuccess() {
        localApiMeeting.addNewMeeting(meetingOne);
        mMeetingPlaceFiltered = localApiMeeting.filteringOptions("Salle 1");
        assertTrue(mMeetingPlaceFiltered.contains(meetingOne));
    }

    @Test
    public void filterPlaceNameWithNoSuccess() {
        localApiMeeting.addNewMeeting(meetingOne);
        mMeetingPlaceFiltered = localApiMeeting.filteringOptions("Salle 3");
    }

}
