package com.lamzone.mareunion.fakeServices;

import com.lamzone.mareunion.model.PlaceItem;

import java.util.List;

/**
 * adding fakeplaceapiservice to
 * 1/reduce code in app
 * 2/to ease reading and maintain code
 */

public interface FakeApiPlace {

    List<PlaceItem> getPlaceItem();

    List<String> getFakePlaceNames();

}
