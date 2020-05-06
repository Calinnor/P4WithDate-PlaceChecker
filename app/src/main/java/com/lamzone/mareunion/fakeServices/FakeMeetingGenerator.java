package com.lamzone.mareunion.fakeServices;

import com.lamzone.mareunion.R;
import com.lamzone.mareunion.model.Meeting;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

class FakeMeetingGenerator {

    private static List<Meeting> FAKE_MEETINGS= Arrays.asList(
            new Meeting(R.drawable.bleu,
                    "Test réunion: Objet ",
                    "- 8h30 -",
                    "Salle 1",
                    "Jack@Email - Joe@Email - Jess@Email",
                    /*"17/05/21"*/setDate()),
            new Meeting(R.drawable.rose,
                    "Test réunion: sujet 2 ",
                    "- 9h30 -",
                    "Salle 2",
                    "Jhon@Email - Jenny@Email - Joel@Email",
                    "17/05/21"),
            new Meeting(R.drawable.vert,
                    "Test réunion: sujet 3 ",
                    "- 10h30 -",
                    "Salle 3",
                    "Joss@Email - Johane@Email - Jessy@Email",
                    "17/05/21")
    );

    static List<Meeting> generateFakeMeeting() {
        return new ArrayList<>(FAKE_MEETINGS);
    }

    private static String setDate(){
    Calendar calendar = Calendar.getInstance();
    String currentDate = String.valueOf(DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime()));
    return currentDate;
    }
}
