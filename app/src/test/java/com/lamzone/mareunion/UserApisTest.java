package com.lamzone.mareunion;

import com.lamzone.mareunion.di.DI;
import com.lamzone.mareunion.model.services.ApiMeeting;
import com.lamzone.mareunion.model.services.ApiPlace;
import com.lamzone.mareunion.model.items.Meeting;
import com.lamzone.mareunion.model.items.PlaceItem;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    private List<Meeting> mMeetingDateFiltered = new ArrayList<>();
    List<Meeting> mMeetingPlaceFiltered = new ArrayList<>();
    private String timeDialogBox = "";
    private String enterDate = "";

    @Before
    public void setupPlace() {
        mApiPlace = DI.getNewInstancePlaceApi();
    }

    @Before
    public void setupMeeting() {
        mApiMeeting = DI.getNewInstanceApi();
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
                "17/05/21");
        int baseSize = mApiMeeting.getMeeting().size();
        assertFalse(mApiMeeting.getMeeting().contains(meetingToAdd));
        mApiMeeting.addNewMeeting(meetingToAdd);
        assertTrue(mApiMeeting.getMeeting().size() == baseSize + 1);
        assertTrue(mApiMeeting.getMeeting().contains(meetingToAdd));
    }

    @Test
    public void deleteMeetingWithSuccessFromList() {
        Meeting meetingToDelete = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21");
        mApiMeeting.addNewMeeting(meetingToDelete);
        int sizeToFind = mApiMeeting.getMeeting().size() - 1;
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
                "17/05/21");
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
                "17/05/21");
        Meeting meetingTwoToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21");
        mApiMeeting.addNewMeeting(meetingOneToAdd);
        mApiMeeting.addNewMeeting(meetingTwoToAdd);
        Meeting meetingToDelete = mApiMeeting.getMeeting().get(0);
        mApiMeeting.deleteMeeting(meetingToDelete);
        assertTrue(mApiMeeting.getMeeting().get(0) != meetingToDelete);
        assertFalse(mApiMeeting.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void getMeetingWithSuccessSamePlaceItem() {
        Meeting meetingToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21");
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
                "17/05/21");
        mApiMeeting.addNewMeeting(meetingToAdd);
        Meeting exceptedMeeting = mApiMeeting.getMeeting().get(mApiMeeting.getMeeting().size() + 1);
        assertTrue(mApiMeeting.getMeeting().contains(exceptedMeeting));
    }

    @Test
    public void getMeetingWithSucces() {
        mApiMeeting.getMeeting().clear();
        Meeting meetingToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21");
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
        String exceptedPlaceName = mApiPlace.getPlaceNames().get(0);
        assertNotNull(exceptedPlaceName);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getFakePlaceNameOutOfList() {
        String exceptedPlaceName = mApiPlace.getPlaceNames().get(mApiPlace.getPlaceNames().size() + 1);
        assertTrue(mApiPlace.getPlaceNames().contains(exceptedPlaceName));
    }

    @Test
    public void getFakePlaceNameWithSuccessSamePlaceItem() {
        String exceptedPlaceName = mApiPlace.getPlaceNames().get(3);
        assertEquals(mApiPlace.getPlaceNames().get(3), exceptedPlaceName);
    }

    /**
     * filter tests
     */
    @Test
    public void filterDateWithSuces() {
        mApiMeeting.getMeeting().clear();
        Meeting meetingToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21");
        Meeting secondMeetingToAdd = new Meeting(R.drawable.bleu,
                "Test2 réunion: Objet ",
                "- 9h30 -",
                "Salle21",
                "Jack@Email - Joe@Email - Jess@Email",
                "18/05/21");
        mApiMeeting.addNewMeeting(meetingToAdd);
        mApiMeeting.addNewMeeting(secondMeetingToAdd);
        assertTrue(mApiMeeting.getMeeting().size() == 2);
        mMeetingDateFiltered = mApiMeeting.filterDate("17/05/21");
        assertTrue(mMeetingDateFiltered.contains(meetingToAdd));
    }

    @Test
    public void filterPlaceNameWithSucces() {
        mApiMeeting.getMeeting().clear();
        Meeting meetingToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21");
        Meeting secondMeetingToAdd = new Meeting(R.drawable.bleu,
                "Test2 réunion: Objet ",
                "- 9h30 -",
                "Salle2",
                "Jack@Email - Joe@Email - Jess@Email",
                "18/05/21");
        mApiMeeting.addNewMeeting(meetingToAdd);
        mApiMeeting.addNewMeeting(secondMeetingToAdd);
        assertTrue(mApiMeeting.getMeeting().size() == 2);
        mMeetingPlaceFiltered = mApiMeeting.filterPlaceName("Salle2");
        assertTrue(mMeetingPlaceFiltered.contains(secondMeetingToAdd));
    }

}