package com.lamzone.mareunion.model.services;

import com.lamzone.mareunion.model.items.PlaceItem;

import java.util.List;

/**
 * adding fakeplaceapiservice to
 * 1/reduce code in app
 * 2/to ease reading and maintain code
 */

public interface LocalApiPlace {

    List<PlaceItem> getPlaceItem();

    List<String> getPlaceNames();

}
