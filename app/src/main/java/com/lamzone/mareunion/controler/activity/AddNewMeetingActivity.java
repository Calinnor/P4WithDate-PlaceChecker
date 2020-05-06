package com.lamzone.mareunion.controler.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lamzone.mareunion.R;
import com.lamzone.mareunion.controler.fragment.DatePickerFragment;
import com.lamzone.mareunion.controler.fragment.TimePickeFragment;
import com.lamzone.mareunion.di.DI;
import com.lamzone.mareunion.fakeServices.FakeApiMeeting;
import com.lamzone.mareunion.fakeServices.FakeApiPlace;
import com.lamzone.mareunion.model.Meeting;
import com.lamzone.mareunion.model.PlaceItem;
import com.lamzone.mareunion.view.recycler.MailListRecyclerViewAdapter;
import com.lamzone.mareunion.view.recycler.PlaceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddNewMeetingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, MailListRecyclerViewAdapter.MailsToDelete {

    private FakeApiMeeting mFakeApiMeeting;
    private FakeApiPlace mFakeApiPlace;

    @BindView(R.id.createNewMeeting)
    Button saveButton;

    @BindView(R.id.meeting_object)
    EditText meetingObject;
    private String mObjectOfMeeting = "";

    @BindView(R.id.time_start_dialogbox)
    TextView timeDialogBox;
    private String mMeetingHour = "";

    @BindView(R.id.enterDate) TextView enterDate;
    private String mMeetingDate = "";

    @BindView(R.id.place_choice) TextView placeChoice;
    @BindView(R.id.spinner_place)
    Spinner spinnerPlace;
    private int clickedColorPlaceTag;
    private ArrayList<PlaceItem> mPlaceItemsList;
    private String mMeetingPlace = "";

    private List<String> mMailsList = new ArrayList<>();
    @BindView(R.id.add_meeting_mails_list_recyclerview)
    RecyclerView mMailRecyclerView;
    @BindView(R.id.add_mails_button) Button mAddMailsButton;
    @BindView(R.id.enter_participant_mail) EditText mEnterParticipantMail;
    private String mParticipants = "";

    @BindView(R.id.toolbar_new_meeting) Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        ButterKnife.bind(this);
        configureRecyclerView();
        onAddMailButtonClick();
        mFakeApiMeeting = DI.getFakeMeetingApi();
        mFakeApiPlace = DI.getApiFakePlace();
        addDateToMeeting();
        addTimeToMeeting();
        saveNewMeeting();
        initPlacesList();
        addNewPlace();
        configureToolbar();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (minute < 10 && hourOfDay < 10) {
            timeDialogBox.setText("0"+hourOfDay + "H0" + minute);
        }
        else if (minute < 10) {
            timeDialogBox.setText(hourOfDay + "H0" + minute);
        }
        else if (hourOfDay < 10) {
            timeDialogBox.setText("0"+hourOfDay + "H" + minute);
        }
        else {
            timeDialogBox.setText(hourOfDay + "H" + minute);
        }
        mMeetingHour = String.valueOf(timeDialogBox);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(month<10 && dayOfMonth < 10){
            enterDate.setText("0"+dayOfMonth + "/0" + month + "/" + year);
        }
        else if(month<10){
            enterDate.setText(dayOfMonth + "/0" + month + "/" + year);
        }
        else if(dayOfMonth<10){
            enterDate.setText("0"+dayOfMonth + month + "/" + year);
        }
        else {
            enterDate.setText(dayOfMonth + "/" + month + "/" + year);
        }
        mMeetingDate = String.valueOf(enterDate.getText());
    }

    public void addNewMeeting() {
        Meeting reunion = new Meeting(clickedColorPlaceTag,
                mObjectOfMeeting,
                "- " + timeDialogBox.getText().toString() + " -",
                placeChoice.getText().toString(),
                mParticipants,
                enterDate.getText().toString()
        );
        mFakeApiMeeting.addNewMeeting(reunion);
        finish();
    }

    public void addDateToMeeting() {
        enterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    private void addTimeToMeeting() {
        timeDialogBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickeFragment();
                timePicker.show(getSupportFragmentManager(), "timePicker");
            }
        });
    }

    private void initPlacesList() {
        mPlaceItemsList = new ArrayList<>(mFakeApiPlace.getPlaceItem());
    }

    private void addNewPlace() {
        PlaceAdapter mPlaceAdapter = new PlaceAdapter(this, mPlaceItemsList);
        spinnerPlace.setAdapter(mPlaceAdapter);
        spinnerPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PlaceItem clickedPlaceItem = (PlaceItem) parent.getItemAtPosition(position);
                placeChoice.setText(clickedPlaceItem.getmPlaceName());
                clickedColorPlaceTag = clickedPlaceItem.getmPlaceColorTag();
                mMeetingPlace = String.valueOf(placeChoice.getText());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void clickToDelete(int position) {
        String mail = mMailsList.get(position);
        mMailsList.remove(mail);
        initListMails(mMailsList);
    }

    private void initListMails(List<String> listMails) {
        MailListRecyclerViewAdapter mAdapter = new MailListRecyclerViewAdapter(listMails, this);
        mMailRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void onAddMailButtonClick() {
        mAddMailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = mEnterParticipantMail.getText() +"";

                if("".equals(mParticipants)) {
                    mParticipants = String.valueOf(mEnterParticipantMail.getText());
                }else{
                    mParticipants = mParticipants+", " + mEnterParticipantMail.getText();
                }

                if ("".equals(mParticipants)) {
                    Toast.makeText(AddNewMeetingActivity.this, "Vous devez remplir les informations concernant le/les participants avant de sauvegarder leurs informations.", Toast.LENGTH_LONG).show();
                } else {
                    mMailsList.add(mail);
                    initListMails(mMailsList);
                    mEnterParticipantMail.setText("");
                }
            }
        });
    }

    private void configureRecyclerView() {
        mMailRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mMailRecyclerView.setAdapter(new MailListRecyclerViewAdapter(mMailsList, this));
    }

    private void saveNewMeeting() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mObjectOfMeeting = String.valueOf(meetingObject.getText());
                if ("".equals(mObjectOfMeeting) || "".equals(mMeetingHour) || "".equals(mMeetingDate) || "".equals(mMeetingPlace) || "".equals(mParticipants)) {
                    Toast.makeText(AddNewMeetingActivity.this, "Vous devez remplir toutes les informations avant de sauvegarder une réunion.", Toast.LENGTH_LONG).show();
                } else {
                    addNewMeeting();
                }
            }
        });
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_add_new_meeting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AddNewMeetingActivity.this.finish();
        return super.onOptionsItemSelected(item);
    }
}


