package com.lamzone.mareunion;

import com.lamzone.mareunion.di.DI;
import com.lamzone.mareunion.model.services.ApiMeeting;
import com.lamzone.mareunion.model.services.ApiPlace;
import com.lamzone.mareunion.model.items.Meeting;
import com.lamzone.mareunion.model.items.PlaceItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MeetingServicesTest {


    private ApiMeeting mApiMeeting = DI.getNewInstanceApi();
    private List<Meeting> meetings = mApiMeeting.getMeeting();
    private ApiPlace mApiPlace;
    private List<Meeting> mMeetingDateFiltered = new ArrayList<>();
    private List<Meeting> mMeetingPlaceFiltered = new ArrayList<>();

    @Before
    public void setupPlace() {
        mApiPlace = DI.getNewInstancePlaceApi();
    }

    @Before
    public void setupMeeting() {
        Meeting meeting = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joel@Email - Jess@Email",
                "17/05/21");
        Meeting meetingTwo = new Meeting(R.drawable.bleu,
                "Test réunion: Objet 2 ",
                "- 9h30 -",
                "Salle 2",
                "Jacky@Email - Joe@Email - Jess@Email",
                "17/06/21");
        meetings.add(meeting);
        meetings.add(meetingTwo);
    }

    @After
    public void resetMeeting(){
        ApiMeeting mApiMeeting = DI.getNewInstanceApi();
        mApiMeeting.getMeeting().clear();
    }

    @Test
    public void getPlaceWithSuccess() {
        PlaceItem expectedPlaceItem = mApiPlace.getPlaceItem().get(0);
        assertNotNull(expectedPlaceItem);
        assertFalse(expectedPlaceItem.getmPlaceName().contains("3"));
        PlaceItem expectedPlaceItem2 = mApiPlace.getPlaceItem().get(1);
        assertTrue(expectedPlaceItem2.getmPlaceName().contains("2"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getPlaceItemOutOfList() {
        PlaceItem expectedPlaceItem = mApiPlace.getPlaceItem().get(mApiPlace.getPlaceItem().size() + 1);
        assertTrue(mApiPlace.getPlaceItem().contains(expectedPlaceItem));
    }

    @Test
    public void addMeetingWithSucces() {
        Meeting meetingToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet ",
                "- 8h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jess@Email",
                "17/05/21");
        Meeting meeting = mApiMeeting.getMeeting().get(0);
        int baseSize = mApiMeeting.getMeeting().size();
        assertFalse(mApiMeeting.getMeeting().contains(meetingToAdd));
        mApiMeeting.addNewMeeting(meeting);
        assertTrue(mApiMeeting.getMeeting().size() == baseSize + 1);
        assertTrue(mApiMeeting.getMeeting().contains(meeting));
    }

    @Test
    public void deleteMeetingWithSuccessFromList() {
        int sizeToFind = mApiMeeting.getMeeting().size() - 1;
        mApiMeeting.deleteMeeting(mApiMeeting.getMeeting().get(0));
        assertTrue(mApiMeeting.getMeeting().size() == sizeToFind);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteMeetingOutOfListSize() {
        Meeting meetingToDelete = mApiMeeting.getMeeting().get(4);
        mApiMeeting.deleteMeeting(meetingToDelete);
    }

    @Test
    public void deletedMeetingIsMeetingToDelete() {
        Meeting meetingToDelete = mApiMeeting.getMeeting().get(0);
        mApiMeeting.deleteMeeting(meetingToDelete);
        assertFalse(mApiMeeting.getMeeting().get(0).equals(meetingToDelete));
        assertFalse(mApiMeeting.getMeeting().contains(meetingToDelete));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getMeetingOutOfList() {
        Meeting exceptedMeeting = mApiMeeting.getMeeting().get(mApiMeeting.getMeeting().size() + 1);
        assertTrue(mApiMeeting.getMeeting().contains(exceptedMeeting));
    }

    @Test
    public void getMeetingWithSucces() {
        Meeting meetingToAdd = new Meeting(R.drawable.bleu,
                "Test réunion: Objet 3",
                "- 10h30 -",
                "Salle 1",
                "Jack@Email - Joe@Email - Jessy@Email",
                "18/05/21");
        mApiMeeting.addNewMeeting(meetingToAdd);
        Meeting exceptedMeeting = mApiMeeting.getMeeting().get(2);
        assertNotNull(exceptedMeeting);
        assertTrue(meetingToAdd.equals(exceptedMeeting));
        assertTrue(mApiMeeting.getMeeting().get(0).getMeetingDate().contains("17/05/21"));
        assertFalse(mApiMeeting.getMeeting().get(2).getMeetingDate().contains("17/05/21"));
        assertTrue(mApiMeeting.getMeeting().get(0).getMeetingHour().contains("- 8h30 -"));
        assertFalse(mApiMeeting.getMeeting().get(2).getMeetingHour().contains("- 8h30 -"));
        assertTrue(mApiMeeting.getMeeting().get(1).getMeetingParticipantsInformations().contains("Jacky@Email - Joe@Email - Jess@Email"));
        assertFalse(mApiMeeting.getMeeting().get(1).getMeetingParticipantsInformations().contains("Jack@Email - Joe@Email - Jessy@Email"));
        assertTrue(mApiMeeting.getMeeting().get(1).getMeetingSubject().contains("Test réunion: Objet 2 "));
        assertFalse(mApiMeeting.getMeeting().get(0).getMeetingSubject().contains("Test réunion: Objet 3 "));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getPlaceNameOutOfList() {
        String exceptedPlaceName = mApiPlace.getPlaceNames().get(12);
        assertTrue(mApiPlace.getPlaceNames().contains(exceptedPlaceName));
    }

    @Test
    public void getPlaceNameWithSucces() {
        assertTrue(mApiPlace.getPlaceNames().get(3).contains("4"));
        assertFalse(mApiPlace.getPlaceNames().get(2).contains("5"));
    }

    @Test
    public void filterDateWithSucces() {
        assertTrue(mApiMeeting.getMeeting().size() == 2);
        mMeetingDateFiltered = mApiMeeting.filteringOptions("17/05/21");
        assertTrue(mMeetingDateFiltered.contains(mApiMeeting.getMeeting().get(0)));
    }

    @Test
    public void filterDateWithNoSucces() {
        assertTrue(mApiMeeting.getMeeting().size() == 2);
        mMeetingDateFiltered = mApiMeeting.filteringOptions("17/05/22");
        assertFalse(mMeetingDateFiltered.contains(mApiMeeting.getMeeting().get(0)));
        assertFalse(mMeetingDateFiltered.contains(mApiMeeting.getMeeting().get(1)));
    }

    @Test
    public void filterPlaceNameWithSucces() {
        assertTrue(mApiMeeting.getMeeting().size() == 2);
        mMeetingPlaceFiltered = mApiMeeting.filteringOptions("Salle 2");
        assertTrue(mMeetingPlaceFiltered.contains(mApiMeeting.getMeeting().get(1)));
    }

    @Test
    public void filterPlaceNameWithNoSucces() {
        assertTrue(mApiMeeting.getMeeting().size() == 2);
        mMeetingPlaceFiltered = mApiMeeting.filteringOptions("Salle 3");
        assertFalse(mMeetingPlaceFiltered.contains(mApiMeeting.getMeeting().get(1)));
        assertFalse(mMeetingPlaceFiltered.contains(mApiMeeting.getMeeting().get(0)));
    }

}