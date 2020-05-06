package com.lamzone.mareunion.activity;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.lamzone.mareunion.R;
import com.lamzone.mareunion.controler.activity.MainMeetingActivity;
import com.lamzone.mareunion.di.DI;
import com.lamzone.mareunion.fakeServices.FakeApiMeeting;
import com.lamzone.mareunion.model.Meeting;
import com.lamzone.mareunion.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.lamzone.mareunion.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class MainMeetingActivityTest {

    private FakeApiMeeting mFakeApiMeeting = DI.getNewInstanceFakeApi();
    private List<Meeting> meetings = mFakeApiMeeting.getMeeting();

    @Rule
    public ActivityTestRule<MainMeetingActivity> mActivityRule =
            new ActivityTestRule(MainMeetingActivity.class);


    @Before
    public void setUp() {
        MainMeetingActivity mMainMeetingActivity = mActivityRule.getActivity();
        assertThat(mMainMeetingActivity, notNullValue());
    }

    @Test
    public void meetingList_not_empty() {
        onView(withId(R.id.list_meetings_for_recyclerView))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * class with withItemCount (recycler) and DeleteView are to implement first in utils.
     */
    @Test
    public void useDeleteAMeetingButton_DisplayMeetingList_MinusOne() {
        int meetingSizeToFind = meetings.size() - 1;
        onView(withId(R.id.list_meetings_for_recyclerView)).check(withItemCount(meetings.size()));
        onView(withId(R.id.list_meetings_for_recyclerView)).perform(actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(withId(R.id.list_meetings_for_recyclerView)).check(withItemCount(meetingSizeToFind));
    }

    @Test
    public void addMeetingActivityButton_Launch_OneElement_On_AddMeetingActivity() {
        onView(withId(R.id.add_meeting)).perform(click());
        onView(withId(R.id.enterDate)).check(matches(isDisplayed()));
    }

}