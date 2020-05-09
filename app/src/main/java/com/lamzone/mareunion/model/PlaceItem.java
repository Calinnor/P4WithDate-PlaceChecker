package com.lamzone.mareunion.model;

public class PlaceItem {

    /**
     * spinner java 1/ create a placeitem objet with reference in his layout
     */

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
