package com.lamzone.mareunion.activity;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.rule.ActivityTestRule;

import com.lamzone.mareunion.R;
import com.lamzone.mareunion.controler.activity.AddNewMeetingActivity;
import com.lamzone.mareunion.controler.activity.MainMeetingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.lamzone.mareunion.utils.RecyclerViewItemCountAssertion.withItemCount;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;


public class AddNewMeetingTest {

    @Rule
    public ActivityTestRule<MainMeetingActivity> mActivityRule =
            new ActivityTestRule(MainMeetingActivity.class);
    @Rule
    public ActivityTestRule mActivityNewMeetingRule =
            new ActivityTestRule(AddNewMeetingActivity.class);

    @Before
    public void setUp() {
        MainMeetingActivity mMainMeetingActivity = mActivityRule.getActivity();
        assertThat(mMainMeetingActivity, notNullValue());
        AddNewMeetingActivity mAddNewMeetingActivity = (AddNewMeetingActivity) mActivityNewMeetingRule.getActivity();
        assertThat(mAddNewMeetingActivity, notNullValue());
    }


    @Test
    public void addMailButton_addMail() {
        onView(withId(R.id.add_meeting_mails_list_recyclerview)).check(withItemCount(0));
        onView(withId(R.id.layout_participants_mails)).perform(scrollTo(), click());
        onView(withId(R.id.enter_participant_mail)).perform(replaceText("TestMail"));
        onView(withId(R.id.add_mails_button)).perform(scrollTo(), click());
        onView(withId(R.id.add_meeting_mails_list_recyclerview)).perform(scrollTo(), click());
        onView(withId(R.id.add_meeting_mails_list_recyclerview)).check(withItemCount(1));
    }

    @Test
    public void clickOnPlaceChoiceButton_displaySpinnerPlace(){
        onView(withId(R.id.spinner_place)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.place_choice)).check(matches(withText(containsString("Salle 1"))));
    }

    @Test
    public void clickOnHourChoiceButton_AndSelect_ThenTimePicker_DisplayChoiceHour_InTextView(){
       onView(withId(R.id.time_start_dialogbox)).perform(click());
       onView(isAssignableFrom(TimePicker.class)).perform(setTime(10, 10));
       onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_start_dialogbox)).check(matches(allOf(withText("10H10"),
                isDisplayed())));
    }

    @Test
    public void clickOnDateChoiceButton_AndSelect_ThenDatePicker_DisplayDateChoice_InTextView(){
        onView(withId(R.id.enterDate)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(1980, 10, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.enterDate)).check(matches(allOf(withText("30/09/1980"),
                isDisplayed())));
    }

    @Test
    public void clickOnReturnButton_displayMainMeeting(){
        onView(withId(R.id.back_button)).perform(click());
        onView(withId(R.id.add_meeting)).check(matches(isDisplayed()));
    }

    @Test
    public void saveButton_display_mainMeetingActivity_onceAllInformations_get(){

        onView(withId(R.id.meeting_object)).perform(replaceText("Test"));

        onView(withId(R.id.spinner_place)).perform(click());
        onData(anything()).atPosition(0).perform(click());

        onView(withId(R.id.time_start_dialogbox)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(10, 10));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.enterDate)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(1980, 10, 30));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.enter_participant_mail)).perform(replaceText("TestMail"));
        onView(withId(R.id.add_mails_button)).perform(scrollTo(), click());

        onView(withId(R.id.createNewMeeting)).perform(click());
        onView(withId(R.id.add_meeting)).check(matches(isDisplayed()));
    }
}
