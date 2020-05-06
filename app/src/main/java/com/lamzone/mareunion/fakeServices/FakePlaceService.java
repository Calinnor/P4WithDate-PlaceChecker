package com.lamzone.mareunion.fakeServices;

import com.lamzone.mareunion.model.PlaceItem;

import java.util.List;

public class FakePlaceService implements FakeApiPlace{

      private List<PlaceItem> mPlaceItems= FakePlaceGenerator.generateFakePlace();
      private List<String> mFakePlaceNames= FakePlaceGenerator.generateFakePlaceNames();

      public void setmPlaceItems(List<PlaceItem> mPlaceItems){
          this.mPlaceItems= mPlaceItems;
      }
      public void setmFakePlaceNames(List<String> mFakePlaceNames) {this.mFakePlaceNames= mFakePlaceNames; }


    @Override
    public List<PlaceItem> getPlaceItem() {
        return mPlaceItems;
    }

    @Override
    public List<String> getFakePlaceNames() {
        return mFakePlaceNames;
    }


}
