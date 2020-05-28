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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lamzone.mareunion.R;
import com.lamzone.mareunion.controler.fragment.DatePickerFragment;
import com.lamzone.mareunion.controler.fragment.TimePickeFragment;
import com.lamzone.mareunion.di.DI;
import com.lamzone.mareunion.model.services.ApiMeeting;
import com.lamzone.mareunion.model.services.ApiPlace;
import com.lamzone.mareunion.model.items.Meeting;
import com.lamzone.mareunion.model.items.PlaceItem;
import com.lamzone.mareunion.utils.DateUtils;
import com.lamzone.mareunion.view.recycler.MailListRecyclerViewAdapter;
import com.lamzone.mareunion.view.recycler.PlaceAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddNewMeetingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, MailListRecyclerViewAdapter.MailsToDelete {

    private ApiMeeting mApiMeeting;
    private ApiPlace mApiPlace;

    private Date timeDateStart;
    private Date timeDateEnd;
    private long dateDisponibility;
    private int browseMeeting;

    private int selectedTime;

    @BindView(R.id.createNewMeeting)
    Button saveButton;

    @BindView(R.id.meeting_object)
    EditText meetingObject;
    private String mObjectOfMeeting = "";

    @BindView(R.id.time_start_dialogbox)
    TextView startTimeDialogBox;
    private String mMeetingStartHour = "";

    @BindView(R.id.time_end_dialogbox)
    TextView endTimeDialogBox;
    private String mMeetingEndHour = "";

    @BindView(R.id.enterDate)
    TextView enterDate;
    private String mMeetingDate = "";

    @BindView(R.id.place_choice)
    TextView placeChoice;
    @BindView(R.id.spinner_place)
    Spinner spinnerPlace;
    private int clickedColorPlaceTag;
    private ArrayList<PlaceItem> mPlaceItemsList;
    private String mMeetingPlace = "";

    private List<String> mMailsList = new ArrayList<>();
    @BindView(R.id.add_meeting_mails_list_recyclerview)
    RecyclerView mMailRecyclerView;
    @BindView(R.id.add_mails_button)
    Button mAddMailsButton;
    @BindView(R.id.enter_participant_mail)
    EditText mEnterParticipantMail;
    private String mParticipants = "";

    @BindView(R.id.toolbar_new_meeting)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        ButterKnife.bind(this);
        configureRecyclerView();
        onAddMailButtonClick();
        mApiMeeting = DI.getMeetingApi();
        mApiPlace = DI.getApiPlace();
        addDateToMeeting();
        addEndTimeToMeeting();
        addStartTimeToMeeting();
        saveNewMeeting();
        initPlacesList();
        addNewPlace();
        configureToolbar();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (selectedTime == 0) {
            startTimeDialogBox.setText(DateUtils.timePickerSet(hourOfDay, minute));
            String startingHour = hourOfDay + ":" + minute;
            try {
                timeDateStart = new SimpleDateFormat("HH:mm").parse(startingHour);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (timeDateEnd == null) {
                mMeetingStartHour = DateUtils.timePickerSet(hourOfDay, minute);
            } else {
                if (timeDateEnd.after(timeDateStart)) {
                    mMeetingStartHour = DateUtils.timePickerSet(hourOfDay, minute);
                } else {
                    Toast.makeText(AddNewMeetingActivity.this, "Vous ne pouvez pas avoir une heure de fin precedant l'heure de début.", Toast.LENGTH_LONG).show();
                    startTimeDialogBox.setText(null);
                }
            }
        } else if (selectedTime == 1) {
            String endingHour = hourOfDay + ":" + minute;
            endTimeDialogBox.setText(DateUtils.timePickerSet(hourOfDay, minute));
            try {
                timeDateEnd = new SimpleDateFormat("HH:mm").parse(endingHour);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (timeDateStart == null) {
                endTimeDialogBox.setText(DateUtils.timePickerSet(hourOfDay, minute));
                mMeetingEndHour = String.valueOf(endTimeDialogBox);
            } else {
                if (timeDateEnd.after(timeDateStart)) {
                    endTimeDialogBox.setText(DateUtils.timePickerSet(hourOfDay, minute));
                    mMeetingEndHour = String.valueOf(endTimeDialogBox);
                } else {
                    Toast.makeText(AddNewMeetingActivity.this, "Vous ne pouvez pas avoir une heure de fin precedant l'heure de début.", Toast.LENGTH_LONG).show();
                    endTimeDialogBox.setText(null);
                }
            }
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        enterDate.setText(DateUtils.datePickerSet(year, month + 1, dayOfMonth));
        mMeetingDate = DateUtils.datePickerSet(year, month + 1, dayOfMonth);
    }

    public void addNewMeeting() {
        Meeting meeting = new Meeting(clickedColorPlaceTag,
                mObjectOfMeeting,
                "-" + startTimeDialogBox.getText().toString() + "-",
                endTimeDialogBox.getText().toString(),
                placeChoice.getText().toString(),
                mParticipants,
                enterDate.getText().toString(),
                dateDisponibility = timeDateEnd.getTime()
        );
        mApiMeeting.addNewMeeting(meeting);
        finish();
    }

    public void addDateToMeeting() {
        enterDate.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "datePicker");
        });
    }

    private void addStartTimeToMeeting() {

        startTimeDialogBox.setOnClickListener(v -> {
            selectedTime = 0;
            DialogFragment startTimePicker = new TimePickeFragment();
            startTimePicker.show(getSupportFragmentManager(), "timePicker");
        });
    }

    private void addEndTimeToMeeting() {
        endTimeDialogBox.setOnClickListener(v -> {
            selectedTime = 1;
            DialogFragment endTimePicker = new TimePickeFragment();
            endTimePicker.show(getSupportFragmentManager(), "timePicker2");
        });
    }


    private void initPlacesList() {
        mPlaceItemsList = new ArrayList<>(mApiPlace.getPlaceItem());
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
        mAddMailsButton.setOnClickListener(view -> {
            String mail = mEnterParticipantMail.getText() + "";

            if (!mail.matches(".+@.+\\.[a-z]+")) {
                Toast.makeText(AddNewMeetingActivity.this, "Vous devez remplir un mail valide avant de sauvegarder les informations.", Toast.LENGTH_SHORT).show();
            } else if ("".equals(mParticipants)) {
                mParticipants = mail;
                addMailToMailsList();
            } else {
                mParticipants = mParticipants + ", " + mail;
                addMailToMailsList();
            }
        });
    }

    private void configureRecyclerView() {
        mMailRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mMailRecyclerView.setAdapter(new MailListRecyclerViewAdapter(mMailsList, this));
    }

    private void saveNewMeeting() {
        saveButton.setOnClickListener(v -> {
            mObjectOfMeeting = String.valueOf(meetingObject.getText());
            if ("".equals(mObjectOfMeeting) || "".equals(mMeetingStartHour) || "".equals(mMeetingEndHour)|| "".equals(mMeetingDate) || "".equals(mMeetingPlace) || "".equals(mParticipants)) {
                Toast.makeText(AddNewMeetingActivity.this, "Vous devez remplir toutes les informations avant de sauvegarder une réunion.", Toast.LENGTH_LONG).show();
            } else {
                dateDisponibility = timeDateStart.getTime();
                List<Meeting> mMeetingDisponibility = new ArrayList<>();
                List<Meeting> mMeetings = mApiMeeting.getMeeting();

                if (mMeetings.size() == 0) {
                    addNewMeeting();
                } else {
                    if (mMeetingDisponibility.size() < 1) {
                        for (browseMeeting = 0; browseMeeting < mMeetings.size(); browseMeeting++) {
                            if (mMeetings.get(browseMeeting).getMeetingPlaceName().equals(mMeetingPlace)
                                    && mMeetings.get(browseMeeting).getMeetingDate().equals(mMeetingDate)
                                    && dateDisponibility
                                    <= mMeetings.get(browseMeeting).getMeetingDateDisponibility()) {
                                mMeetingDisponibility.add(mMeetings.get(browseMeeting));
                            }
                        }
                    }

                    if (mMeetingDisponibility.size() == 0) {
                        addNewMeeting();
                    } else {
                        Toast.makeText(AddNewMeetingActivity.this, "La salle que vous avez choisi ne sera pas disponible ce jour là avant " + mMeetings.get(browseMeeting - 1).getMeetingEndHour(), Toast.LENGTH_LONG).show();
                    }
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

    private void addMailToMailsList() {
        String mail = mEnterParticipantMail.getText() + "";
        mMailsList.add(mail);
        initListMails(mMailsList);
        mEnterParticipantMail.setText("");
    }
}


