package com.lamzone.mareunion.model.services;

import com.lamzone.mareunion.model.items.PlaceItem;

import java.util.List;

public interface ApiPlace {

    List<PlaceItem> getPlaceItem();

    List<String> getPlaceNames();

}
