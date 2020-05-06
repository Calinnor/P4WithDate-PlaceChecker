package com.lamzone.mareunion.fakeServices;

import com.lamzone.mareunion.R;
import com.lamzone.mareunion.model.PlaceItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FakePlaceGenerator {

    private static List<PlaceItem> FAKE_PLACE= Arrays.asList(
       new PlaceItem(R.drawable.bleumarbre, "1"),
       new PlaceItem(R.drawable.rose, "2"),
       new PlaceItem(R.drawable.vert, "3"),
       new PlaceItem(R.drawable.violet, "4"),
       new PlaceItem(R.drawable.marron, "5"),
       new PlaceItem(R.drawable.chair, "6"),
       new PlaceItem(R.drawable.bordeau, "7"),
       new PlaceItem(R.drawable.gris, "8"),
       new PlaceItem(R.drawable.orange, "9"),
       new PlaceItem(R.drawable.rouge, "10")
    );

    static List<PlaceItem> generateFakePlace(){
        return new ArrayList<>(FAKE_PLACE);
    }

    private static List<String> FAKE_PLACENAME= Arrays.asList(
            "Salle 1", "Salle 2", "Salle 3", "Salle 4", "Salle 5", "Salle 6", "Salle 7", "Salle 8", "Salle 9", "Salle 10"
    );

    static List<String> generateFakePlaceNames(){
        return new ArrayList<>(FAKE_PLACENAME);
    }
}
