package com.lamzone.mareunion.activity;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.lamzone.mareunion.R;
import com.lamzone.mareunion.controler.activity.AddNewMeetingActivity;
import com.lamzone.mareunion.controler.activity.MainMeetingActivity;
import com.lamzone.mareunion.di.DI;
import com.lamzone.mareunion.fakeServices.FakeApiMeeting;
import com.lamzone.mareunion.model.Meeting;
import com.lamzone.mareunion.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.lamzone.mareunion.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class MainMeetingActivityTest {

    @Rule
    public ActivityTestRule<MainMeetingActivity> mActivityRule =
            new ActivityTestRule(MainMeetingActivity.class);

    @Before
    public void setUp() {
        MainMeetingActivity mMainMeetingActivity = mActivityRule.getActivity();
        assertThat(mMainMeetingActivity, notNullValue());
    }

    @Test
    public void meetingList_is_empty() {
        onView(withId(R.id.list_meetings_for_recyclerView))
                .check(matches(hasMinimumChildCount(0)));
    }

    /**
     * class with withItemCount (recycler) and DeleteView are to implement first in utils.
     */


    @Test
    public void addMeetingActivityButton_Launch_OneElement_On_AddMeetingActivity() {
        onView(withId(R.id.add_meeting)).perform(click());
        onView(withId(R.id.meeting_object)).check(matches(isDisplayed()));
    }

    @Test
    public void toolbarItemButton_displayed_itemMenu(){
        onView(withId(R.id.filter_button)).perform(click());
        onView(withText("Toutes les réunions")).check(matches(isDisplayed()));
    }

    @Test
    public void toolbarItemButton_displayed_enu(){
        onView(withId(R.id.filter_button)).perform(click());
        onView(withText("Toutes les réunions")).check(matches(isDisplayed()));
    }

    @Test
    public void filterDateButton_showNoMeeting_whenListAsNoMeeting_checkingGoodDate(){
        onView(withId(R.id.filter_button)).perform(click());
        onView(ViewMatchers.withText("Filtrer les réunions par date")).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(21, 06, 17));
        onView(withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(R.id.list_meetings_for_recyclerView)).check(withItemCount(0));
    }

}