package com.lamzone.mareunion;

import com.lamzone.mareunion.di.DI;
import com.lamzone.mareunion.fakeServices.ApiMeeting;
import com.lamzone.mareunion.fakeServices.ApiPlace;
import com.lamzone.mareunion.model.Meeting;
import com.lamzone.mareunion.model.PlaceItem;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UserApisTest {


    private ApiMeeting mApiMeeting;
    private ApiPlace mApiPlace;

    @Before
    public void setupPlace() {
        mApiPlace = DI.getNewInstanceFakePlaceApi();
    }

    @Before
    public void setupMeeting() {
        mApiMeeting = DI.getNewInstanceFakeApi();
    }

    /**
     * tests for PlaceItem
     */
    @Test
    public void getPlacesWithSuccess() {
        PlaceItem expectedPlaceItem = mApiPlace.getPlaceItem().get(0);
        assertNotNull(expectedPlaceItem);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getPlaceItemOutOfList() {
        PlaceItem expectedPlaceItem = mApiPlace.getPlaceItem().get(mApiPlace.getPlaceItem().size() + 1);
        assertTrue(mApiPlace.getPlaceItem().contains(expectedPlaceItem));
    }

    @Test
    public void getPlaceItemWithSuccessSamePlaceItem() {
        PlaceItem expectedPlaceItem = mApiPlace.getPlaceItem().get(2);
        assertEquals(mApiPlace.getPlaceItem().get(2), expectedPlaceItem);
    }

    /**
     * tests for Meeting
     */

    @Test
    public void addMeetingWithSucces() {
    Meeting meetingToAdd = new Meeting(R.drawable.bleu,
        "Test réunion: Objet ",
        "- 8h30 -",
        "Salle 1",
        "Jack@Email - Joe@Email - Jess@Email",
        "17/05/21" );
        int baseSize = mApiMeeting.getMeeting().size();
        assertFalse(mApiMeeting.getMeeting().contains(meetingToAdd));
        mApiMeeting.addNewMeeting(meetingToAdd);
        assertTrue(mApiMeeting.getMeeting().size() == baseSize + 1 );
        assertTrue(mApiMeeting.getMeeting().contains(meetingToAdd));
    }

    @Test
    public void deleteMeetingWithSuccessFromList() {
        Meeting meetingToDelete = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21" );
        mApiMeeting.addNewMeeting(meetingToDelete);
        int sizeToFind = mApiMeeting.getMeeting().size() -1;
        assertTrue(mApiMeeting.getMeeting().contains(meetingToDelete));
        mApiMeeting.deleteMeeting(meetingToDelete);
        assertFalse(mApiMeeting.getMeeting().contains(meetingToDelete));
        assertTrue(mApiMeeting.getMeeting().size() == sizeToFind);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteMeetingOutOfListSize() {
        Meeting meetingToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21" );
        mApiMeeting.addNewMeeting(meetingToAdd);
        Meeting meetingToDelete = mApiMeeting.getMeeting().get(mApiMeeting.getMeeting().size() + 1);
        mApiMeeting.deleteMeeting(meetingToDelete);
    }

    @Test
    public void deletedMeetingIsMeetingToDelete() {
        Meeting meetingOneToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21" );
        Meeting meetingTwoToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21" );
        mApiMeeting.addNewMeeting(meetingOneToAdd);
        mApiMeeting.addNewMeeting(meetingTwoToAdd);
        Meeting meetingToDelete = mApiMeeting.getMeeting().get(0);
        mApiMeeting.deleteMeeting(meetingToDelete);
        assertTrue(mApiMeeting.getMeeting().get(0) != meetingToDelete);
        assertFalse( mApiMeeting.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void getMeetingWithSuccessSamePlaceItem() {
        Meeting meetingToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21" );
        mApiMeeting.addNewMeeting(meetingToAdd);
        Meeting expectedMeeting = mApiMeeting.getMeeting().get(0);
        assertEquals(mApiMeeting.getMeeting().get(0), expectedMeeting);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getMeetingOutOfList() {
        Meeting meetingToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21" );
        mApiMeeting.addNewMeeting(meetingToAdd);
        Meeting exceptedMeeting = mApiMeeting.getMeeting().get(mApiMeeting.getMeeting().size() + 1);
        assertTrue(mApiMeeting.getMeeting().contains(exceptedMeeting));
    }

    @Test
    public void getMeetingWithSucces() {
        Meeting meetingToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21" );
        mApiMeeting.addNewMeeting(meetingToAdd);
        Meeting exceptedMeeting = mApiMeeting.getMeeting().get(0);
        assertNotNull(exceptedMeeting);
        assertTrue(meetingToAdd.equals(exceptedMeeting));
    }

    /**
     * tests for FakePlaceName
     */
    @Test
    public void getFakePlaceNameWithSucces() {
        String exceptedPlaceName = mApiPlace.getFakePlaceNames().get(0);
        assertNotNull(exceptedPlaceName);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getFakePlaceNameOutOfList() {
        String exceptedPlaceName = mApiPlace.getFakePlaceNames().get(mApiPlace.getFakePlaceNames().size() + 1);
        assertTrue(mApiPlace.getFakePlaceNames().contains(exceptedPlaceName));
    }

    @Test
    public void getFakePlaceNameWithSuccessSamePlaceItem() {
        String exceptedPlaceName = mApiPlace.getFakePlaceNames().get(3);
        assertEquals(mApiPlace.getFakePlaceNames().get(3), exceptedPlaceName);
    }

    @Test
    public void filterDateWithSuces(){
        //entrer deux  dates differentes verifier si les dates sont presente , verifier si l'affichage donne bien une date
        //filterdate
    };

    @Test
    public void filterPlaceName(){};//filterplacename

    @Test
    public void timePickerSet(){};

    @Test
    public void datePickerSet(){};





}