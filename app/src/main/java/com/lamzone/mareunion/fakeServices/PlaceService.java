package com.lamzone.mareunion.fakeServices;

import com.lamzone.mareunion.model.Meeting;
import com.lamzone.mareunion.model.PlaceItem;

import java.util.ArrayList;
import java.util.List;

public class PlaceService implements ApiPlace {
    private List<PlaceItem> mPlaceItems = PlaceGenerator.generateFakePlace();
    private List<String> mFakePlaceNames = PlaceGenerator.generateFakePlaceNames();

    @Override
    public List<PlaceItem> getPlaceItem() {
        return mPlaceItems;
    }

    @Override
    public List<String> getFakePlaceNames() {
        return mFakePlaceNames;
    }



}
