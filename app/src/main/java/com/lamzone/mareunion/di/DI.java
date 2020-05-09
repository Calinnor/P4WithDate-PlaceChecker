package com.lamzone.mareunion.di;

import com.lamzone.mareunion.fakeServices.FakeApiMeeting;
import com.lamzone.mareunion.fakeServices.FakeApiPlace;
import com.lamzone.mareunion.fakeServices.FakeMeetingService;
import com.lamzone.mareunion.fakeServices.FakePlaceService;

/**
 * create instance of FakeMeetingService using ApiService service
 */
public class DI {

    /**
     * implemente FakeApi
     */
    private static FakeApiMeeting mFakeMeetingApi = new FakeMeetingService();
    private static FakeApiPlace mApiFakePlace = new FakePlaceService();

    /**
     * @return an instance of FakeApi. Usable in app to collect values
     */
    public static FakeApiMeeting getFakeMeetingApi() {
        return mFakeMeetingApi;
    }

    public static FakeApiPlace getApiFakePlace() {
        return mApiFakePlace;
    }

    /**
     * @return a new instance of DummyMeetingService. Usable with tests
     */
    public static FakeApiMeeting getNewInstanceFakeApi() {
        return new FakeMeetingService();
    }

    public static FakeApiPlace getNewInstanceFakePlaceApi() {
        return new FakePlaceService();
    }
}
