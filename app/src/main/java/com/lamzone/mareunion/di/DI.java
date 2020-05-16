package com.lamzone.mareunion.di;

import com.lamzone.mareunion.fakeServices.ApiMeeting;
import com.lamzone.mareunion.fakeServices.ApiPlace;
import com.lamzone.mareunion.fakeServices.MeetingService;
import com.lamzone.mareunion.fakeServices.PlaceService;

/**
 * create instance of FakeMeetingService using ApiService service
 */
public class DI {

    /**
     * implemente FakeApi
     */
    private static ApiMeeting mFakeMeetingApi = new MeetingService();
    private static ApiPlace mApiFakePlace = new PlaceService();

    /**
     * @return an instance of FakeApi. Usable in app to collect values
     */
    public static ApiMeeting getFakeMeetingApi() {
        return mFakeMeetingApi;
    }

    public static ApiPlace getApiFakePlace() {
        return mApiFakePlace;
    }

    /**
     * @return a new instance of DummyMeetingService. Usable with tests
     */
    public static ApiMeeting getNewInstanceFakeApi() {
        return new MeetingService();
    }

    public static ApiPlace getNewInstanceFakePlaceApi() {
        return new PlaceService();
    }
}
