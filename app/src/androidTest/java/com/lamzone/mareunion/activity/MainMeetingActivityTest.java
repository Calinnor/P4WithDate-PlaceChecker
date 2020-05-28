package com.lamzone.mareunion.activity;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.lamzone.mareunion.R;
import com.lamzone.mareunion.controler.activity.MainMeetingActivity;
import com.lamzone.mareunion.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.lamzone.mareunion.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class MainMeetingActivityTest {

    @Rule
    public ActivityTestRule<MainMeetingActivity> mActivityRule =
            new ActivityTestRule<>(MainMeetingActivity.class);

    @Before
    public void setUp() {
        MainMeetingActivity mMainMeetingActivity = mActivityRule.getActivity();
        assertThat(mMainMeetingActivity, notNullValue());
    }

    //TODO @after pour reinitialiser apres chaque test a trouver comment ca fonctionne afin de ne pas avoir a utiliser delete

    @Test
    public void meetingList_is_empty() {
        onView(withId(R.id.list_meetings_for_recyclerView))
                .check(matches(hasMinimumChildCount(0)));
    }

    @Test
    public void addMeetingActivityButton_Launch_OneElement_On_AddMeetingActivity() {
        onView(withId(R.id.add_meeting)).perform(click());
        onView(withId(R.id.meeting_object)).check(matches(isDisplayed()));
    }

    @Test
    public void toolbarItemButton_displayed_itemMenu(){
        onView(withId(R.id.filter_button)).perform(click());
        onView(withText("Toutes les réunions")).check(matches(isDisplayed()));
        onView(withText("Filtrer les réunions par date")).check(matches(isDisplayed()));
        onView(withText("Filtrer les réunions par lieu")).check(matches(isDisplayed()));
    }

    @Test
    public void filterAllMeetingItem_WithSucces(){
        onView(withId(R.id.filter_button)).perform(click());
        onView(withText("Toutes les réunions")).check(matches(isDisplayed()));
        onView(withText("Toutes les réunions")).perform(click());
        onView(withId(R.id.list_meetings_for_recyclerView)).check(matches(hasMinimumChildCount(0)));
        onView(withId(R.id.add_meeting)).perform(click());

        onView(withId(R.id.meeting_object)).perform(replaceText("Test"));

        onView(withId(R.id.spinner_place)).perform(click());
        onData(anything()).atPosition(0).perform(click());

        onView(withId(R.id.time_start_dialogbox)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(10, 10));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.time_end_dialogbox)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(12, 00));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.enterDate)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(1980, 10, 30));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.enter_participant_mail)).perform(replaceText("TestMail@test.mail"));
        onView(withId(R.id.add_mails_button)).perform(scrollTo(), click());

        onView(withId(R.id.createNewMeeting)).perform(click());
        onView(withId(R.id.list_meetings_for_recyclerView)).check(matches(isDisplayed()));

        onView(withId(R.id.filter_button)).perform(click());

        onView(ViewMatchers.withText("Toutes les réunions")).check(matches(isDisplayed()));
        onView(ViewMatchers.withText("Toutes les réunions")).perform(click());
        onView(withId(R.id.list_meetings_for_recyclerView)).check(matches(hasMinimumChildCount(1)));
        onView(withId(R.id.list_meetings_for_recyclerView)).perform(actionOnItemAtPosition(0, new DeleteViewAction()));
    }

    @Test
    public void filterDateButton_WithSucces(){
        onView(withId(R.id.filter_button)).perform(click());
        onView(withText("Filtrer les réunions par date")).check(matches(isDisplayed()));
        onView(ViewMatchers.withText("Filtrer les réunions par date")).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(21, 6, 17));
        onView(withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(R.id.list_meetings_for_recyclerView)).check(withItemCount(0));

        onView(withId(R.id.add_meeting)).perform(click());

        onView(withId(R.id.meeting_object)).perform(replaceText("Test"));

        onView(withId(R.id.spinner_place)).perform(click());
        onData(anything()).atPosition(0).perform(click());

        onView(withId(R.id.time_start_dialogbox)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(10, 10));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.time_end_dialogbox)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(12, 00));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.enterDate)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(1980, 10, 30));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.enter_participant_mail)).perform(replaceText("TestMail@test.mail"));
        onView(withId(R.id.add_mails_button)).perform(scrollTo(), click());

        onView(withId(R.id.createNewMeeting)).perform(click());
        onView(withId(R.id.list_meetings_for_recyclerView)).check(matches(isDisplayed()));

        onView(withId(R.id.filter_button)).perform(click());

        onView(ViewMatchers.withText("Filtrer les réunions par date")).check(matches(isDisplayed()));
        onView(ViewMatchers.withText("Filtrer les réunions par date")).perform(click());

        onView(isAssignableFrom(DatePicker.class)).perform(setDate(1980, 10, 30));
        onView(withId(android.R.id.button1)).perform(click());

        onView(ViewMatchers.withId(R.id.list_meetings_for_recyclerView)).check(withItemCount(1));
        onView(withId(R.id.list_meetings_for_recyclerView)).perform(actionOnItemAtPosition(0, new DeleteViewAction()));


    }

    @Test
    public void filterPlaceButton_WithSucces(){
        onView(withId(R.id.filter_button)).perform(click());

        onView(ViewMatchers.withText("Filtrer les réunions par lieu")).check(matches(isDisplayed()));
        onView(ViewMatchers.withText("Filtrer les réunions par lieu")).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(R.id.list_meetings_for_recyclerView)).check(withItemCount(0));

        onView(withId(R.id.add_meeting)).perform(click());

        onView(withId(R.id.meeting_object)).perform(replaceText("Test"));

        onView(withId(R.id.spinner_place)).perform(click());
        onData(anything()).atPosition(0).perform(click());

        onView(withId(R.id.time_start_dialogbox)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(10, 10));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.time_end_dialogbox)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(12, 00));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.enterDate)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(1980, 10, 30));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.enter_participant_mail)).perform(replaceText("TestMail@test.mail"));
        onView(withId(R.id.add_mails_button)).perform(scrollTo(), click());

        onView(withId(R.id.createNewMeeting)).perform(click());
        onView(withId(R.id.list_meetings_for_recyclerView)).check(matches(isDisplayed()));

        onView(withId(R.id.filter_button)).perform(click());

        onView(ViewMatchers.withText("Filtrer les réunions par lieu")).check(matches(isDisplayed()));
        onView(ViewMatchers.withText("Filtrer les réunions par lieu")).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        onView(ViewMatchers.withId(R.id.list_meetings_for_recyclerView)).check(withItemCount(1));
        onView(withId(R.id.list_meetings_for_recyclerView)).perform(actionOnItemAtPosition(0, new DeleteViewAction()));

    }

}