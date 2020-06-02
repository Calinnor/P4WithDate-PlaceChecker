package com.lamzone.mareunion;

import com.lamzone.mareunion.di.DI;
import com.lamzone.mareunion.model.items.Meeting;
import com.lamzone.mareunion.model.items.PlaceItem;
import com.lamzone.mareunion.model.services.ApiMeeting;
import com.lamzone.mareunion.model.services.ApiPlace;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PlaceServiceTest {

    private ApiPlace mApiPlace;
    private ApiMeeting apiMeeting;

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
        mApiPlace = DI.getNewInstancePlaceApi();
        apiMeeting = DI.getNewInstanceApi();
    }

    /**
     * PlaceItem tests
     */
    @Test
    public void getPlaceWithSuccess() {
        assertEquals(mApiPlace.getPlaceItem().size(), 10);
        assertTrue(mApiPlace.getPlaceItem().get(1).getmPlaceName().contains("2"));
    }

    @Test
    public void getPlaceWithNoSuccess(){
        PlaceItem expectedPlaceItem = mApiPlace.getPlaceItem().get(0);
        assertNotNull(expectedPlaceItem);
        assertFalse(expectedPlaceItem.getmPlaceName().contains("3"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getPlaceItemOutOfList() {
        PlaceItem expectedPlaceItem = mApiPlace.getPlaceItem().get(mApiPlace.getPlaceItem().size() + 1);
        assertTrue(mApiPlace.getPlaceItem().contains(expectedPlaceItem));
    }

    /**
     * PlaceName tests
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getPlaceNameOutOfList() {
        String exceptedPlaceName = mApiPlace.getPlaceNames().get(12);
        assertTrue(mApiPlace.getPlaceNames().contains(exceptedPlaceName));
    }

    @Test
    public void getPlaceNameWithSuccess() {
        assertTrue(mApiPlace.getPlaceNames().get(3).contains("4"));
        assertTrue(mApiPlace.getPlaceNames().get(2).contains("3"));
    }

    @Test
    public void getPlaceNameWithNoSuccess() {
        assertFalse(mApiPlace.getPlaceNames().get(6).contains("5"));
        assertFalse(mApiPlace.getPlaceNames().get(4).contains("2"));
    }

    /**
     * filter PlaceName tests
     */
    @Test
    public void filterPlaceNameWithSuccess() {
        apiMeeting.addNewMeeting(meetingOne);
        mMeetingPlaceFiltered = apiMeeting.filteringOptions("Salle 1");
        assertTrue(mMeetingPlaceFiltered.contains(meetingOne));
    }

    @Test
    public void filterPlaceNameWithNoSuccess() {
        apiMeeting.addNewMeeting(meetingOne);
        mMeetingPlaceFiltered = apiMeeting.filteringOptions("Salle 3");
    }

}
