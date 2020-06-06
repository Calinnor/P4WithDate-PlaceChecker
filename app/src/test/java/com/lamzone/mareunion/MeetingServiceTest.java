package com.lamzone.mareunion;

import com.lamzone.mareunion.di.DI;
import com.lamzone.mareunion.model.services.ApiMeeting;
import com.lamzone.mareunion.model.items.Meeting;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MeetingServiceTest {

    private ApiMeeting mApiMeeting;

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
        mApiMeeting = DI.getNewInstanceApi();
    }

    /**
     * addMeeting tests
     */
    @Test
    public void addMeetingWithSuccess() {
        assertFalse(mApiMeeting.getMeeting().contains(meetingOne));
        mApiMeeting.addNewMeeting(meetingOne);
        assertEquals(mApiMeeting.getMeeting().size(), 1);
        assertTrue(mApiMeeting.getMeeting().contains(meetingOne));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullMeeting() {
        mApiMeeting.addNewMeeting(null);
    }

    /**
     * getMeeting tests
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getMeetingOutOfList() {
        Meeting exceptedMeeting = mApiMeeting.getMeeting().get(0);
        assertTrue(mApiMeeting.getMeeting().contains(exceptedMeeting));
    }

    @Test
    public void getMeetingWithSuccess() {
        mApiMeeting.addNewMeeting(meetingOne);
        assertTrue(mApiMeeting.getMeeting().get(0).getMeetingDate().contains("17/05/21"));
        assertTrue(mApiMeeting.getMeeting().get(0).getMeetingStartHour().contains("- 8h30 -"));
        assertTrue(mApiMeeting.getMeeting().get(0).getMeetingEndHour().contains("10h00"));
        assertTrue(mApiMeeting.getMeeting().get(0).getMeetingPlaceName().contains("Salle 1"));
        assertTrue(mApiMeeting.getMeeting().get(0).getMeetingParticipantsInformations().contains("Jack@Email - Joel@Email - Jess@Email"));
        assertTrue(mApiMeeting.getMeeting().get(0).getMeetingSubject().contains("Test réunion: Objet "));
        assertTrue(mApiMeeting.getMeeting().contains(meetingOne));
    }

    @Test
    public void getMeetingWithNoSuccess() {
        mApiMeeting.addNewMeeting(meetingOne);
        Meeting loadedMeeting = mApiMeeting.getMeeting().get(0);
        assertFalse(loadedMeeting.getMeetingDate().contains("17/06/21"));
        assertFalse(loadedMeeting.getMeetingStartHour().contains("- 9h30 -"));
        assertFalse(loadedMeeting.getMeetingEndHour().contains("1h00"));
        assertFalse(loadedMeeting.getMeetingPlaceName().contains("Salle 2"));
        assertFalse(loadedMeeting.getMeetingParticipantsInformations().contains("Jacky@Email - Joel@Email - Jess@Email"));
        assertFalse(loadedMeeting.getMeetingSubject().contains("Test réunion: Objet 2"));
        assertTrue(mApiMeeting.getMeeting().contains(meetingOne));
    }

    /**
     * deleteMeeting tests
     */
    @Test
    public void deleteMeetingWithSuccessFromList() {
        mApiMeeting.addNewMeeting(meetingOne);
        assertTrue(mApiMeeting.getMeeting().contains(meetingOne));
        mApiMeeting.deleteMeeting(meetingOne);
        assertFalse(mApiMeeting.getMeeting().contains(meetingOne));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteMeetingOutOfListSize() {
        Meeting meetingToDelete = mApiMeeting.getMeeting().get(4);
        mApiMeeting.deleteMeeting(meetingToDelete);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteEmptyMeeting() {
        mApiMeeting.deleteMeeting(null);
    }

    @Test
    public void deletedMeetingIsMeetingToDelete() {
        Meeting meetingToDelete = meetingOne;
        mApiMeeting.addNewMeeting(meetingOne);
        mApiMeeting.deleteMeeting(meetingToDelete);
        assertFalse(mApiMeeting.getMeeting().contains(meetingOne));
    }

    /**
     * filterDate tests
     */
    @Test
    public void filterDateWithSuccess() {
        mApiMeeting.addNewMeeting(meetingOne);
        List<Meeting> mMeetingDateFiltered = mApiMeeting.filteringOptions("17/05/21");
        assertEquals(1, mMeetingDateFiltered.size());
        assertTrue(mMeetingDateFiltered.contains(meetingOne));
    }

    @Test
    public void filterDateWithNoSucces() {
        mApiMeeting.addNewMeeting(meetingOne);
        List<Meeting> mMeetingDateFiltered = mApiMeeting.filteringOptions("17/05/22");
        assertEquals(1, mMeetingDateFiltered.size());
        assertFalse(mMeetingDateFiltered.contains(meetingOne));
    }

    /**
     * filterName tests
     */
    @Test
    public void filterPlaceNameWithSuccess() {
        mApiMeeting.addNewMeeting(meetingOne);
        List<Meeting> mMeetingPlaceFiltered = mApiMeeting.filteringOptions("Salle 1");
        assertEquals(1, mMeetingPlaceFiltered.size());
        assertTrue(mMeetingPlaceFiltered.contains(meetingOne));
    }

    @Test
    public void filterPlaceNameWithNoSuccess() {
        mApiMeeting.addNewMeeting(meetingOne);
        List<Meeting> mMeetingPlaceFiltered = mApiMeeting.filteringOptions("Salle 3");
        assertEquals(0, mMeetingPlaceFiltered.size());
        assertFalse(mMeetingPlaceFiltered.contains(meetingOne));
    }

}