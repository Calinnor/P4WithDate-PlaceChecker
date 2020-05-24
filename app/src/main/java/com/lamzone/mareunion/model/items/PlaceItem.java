package com.lamzone.mareunion.model.items;

public class PlaceItem {

    private int mPlaceColorTag;
    private String mPlaceName;

    public PlaceItem(int placeColorTag, String placeName) {
        this.mPlaceColorTag = placeColorTag;
        this.mPlaceName = placeName;
    }

    public int getmPlaceColorTag() {
        return mPlaceColorTag;
    }

    public String getmPlaceName() {
        return "Salle " + mPlaceName;
    }

}
