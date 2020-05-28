package com.lamzone.mareunion.controler.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.mareunion.R;
import com.lamzone.mareunion.controler.fragment.DatePickerFragment;
import com.lamzone.mareunion.di.DI;
import com.lamzone.mareunion.model.services.ApiMeeting;
import com.lamzone.mareunion.model.services.ApiPlace;
import com.lamzone.mareunion.model.items.Meeting;
import com.lamzone.mareunion.utils.DateUtils;
import com.lamzone.mareunion.view.event.DeleteMeetingEvent;
import com.lamzone.mareunion.view.recycler.MyMeetingAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    /**
     * recyclerview first step
     * 1/ declare recyclerview
     * 2/ declare api
     * 3/ declare and initiate list of meeting else=crash
     */

    ApiMeeting mApiMeeting;
    ApiPlace mApiPlace;
    private List<Meeting> mMeeting = new ArrayList<>();
    @BindView(R.id.list_meetings_for_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.add_meeting)
    FloatingActionButton addNewMeetingButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    String mDateToFilter;
    @BindView(R.id.lbl_no_task)
    TextView textViewNothingToShow;

    private MyMeetingAdapter myMeetingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_meeting);

        ButterKnife.bind(this);
        mApiMeeting = DI.getMeetingApi();
        mApiPlace = DI.getApiPlace();
        this.configureToolbar();
        clickOnAddNewMeetingButton();
        /**
         * 4/ configure recycler in main
         */
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyMeetingAdapter(mMeeting));
        /**
         * 5/ once name class of recycler given create class (MyMeetingAdapter)
         */
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initEmptyList();
    }

    /**
     * initiate then given life to list of meeting
     */
    private void initList() {
        myMeetingAdapter = new MyMeetingAdapter(mApiMeeting.getMeeting());
        mRecyclerView.setAdapter(myMeetingAdapter);
        selectVisibility();
    }

    private void initEmptyList() {
        mApiMeeting.getMeeting().clear();
        myMeetingAdapter.updateMeetings(mApiMeeting.getMeeting());
        selectVisibility();
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    /**
     * toolbar with menu config
     */
    private void configureToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_meeting_list_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_all_meeting:
                initList();
                break;
            case R.id.filter_meeting_by_date:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "datePickerMain");
                break;
            case R.id.filter_meeting_by_place:
                dialogBoxForPlaceNameFiltering();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * eventbus delete 3/ glue evnetbus with onStart activity
     */
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /**
     * eventbus 4/ glue eventbus with onStop activity
     */
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * eventbus 5/ suscribe to the event DeleteMeetingEvent (which is a class)
     * need to declare interface
     *
     * @param event Fired if the user clicks on a delete button then init a new list
     */
    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mApiMeeting.deleteMeeting(event.mMeeting);
        initList();
    }

    public void clickOnAddNewMeetingButton() {
        addNewMeetingButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainMeetingActivity.this, AddNewMeetingActivity.class);
            startActivity(intent);
        });
    }

    /**
     * this dialog box is to high for me. I'll try to find something else...but it work
     */
    private void dialogBoxForPlaceNameFiltering() {
        List<String> fakePlaceNames = new ArrayList<>(mApiPlace.getPlaceNames());
        String[] placeNamesToFiltered = new String[fakePlaceNames.size()];
        fakePlaceNames.toArray(placeNamesToFiltered);
        final String[] places = new String[1];
        final AlertDialog.Builder builderRoom = new AlertDialog.Builder(this);
        builderRoom.setTitle("Choisissez une Salle");
        builderRoom.setSingleChoiceItems(placeNamesToFiltered, -1, (dialog, placeName) -> places[0] = placeNamesToFiltered[placeName]);
        builderRoom.setPositiveButton("OK", (dialogInterface, i) -> mRecyclerView.setAdapter(new MyMeetingAdapter(mApiMeeting.filteringOptions(places[0]))));
        builderRoom.setNegativeButton("Annuler", (dialog, resetButton) -> initEmptyList());
        AlertDialog dialogRoom = builderRoom.create();
        dialogRoom.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mDateToFilter = DateUtils.datePickerSet(year, month+1, dayOfMonth);
        mRecyclerView.setAdapter(new MyMeetingAdapter(mApiMeeting.filteringOptions(mDateToFilter)));
    }

    private void selectVisibility() {
        if (mApiMeeting.getMeeting().size() == 0) {
            textViewNothingToShow.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            textViewNothingToShow.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }


}
