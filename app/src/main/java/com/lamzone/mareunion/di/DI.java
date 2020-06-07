package com.lamzone.mareunion.di;

import com.lamzone.mareunion.model.services.LocalApiMeeting;
import com.lamzone.mareunion.model.services.LocalApiPlace;
import com.lamzone.mareunion.model.services.LocalMeetingService;
import com.lamzone.mareunion.model.services.PlaceServiceLocal;

/**
 * create instance of LocalMeetingService using ApiService service
 */
public class DI {

    /**
     * implement FakeApi
     */
    private static LocalApiMeeting mMeetingApi = new LocalMeetingService();
    private static LocalApiPlace mLocalApiPlace = new PlaceServiceLocal();

    /**
     * @return an instance of FakeApi. Usable in app to collect values
     */
    public static LocalApiMeeting getMeetingApi() {
        return mMeetingApi;
    }

    public static LocalApiPlace getApiPlace() {
        return mLocalApiPlace;
    }

    /**
     * @return a new instance of DummyMeetingService. Usable with tests
     */
    public static LocalApiMeeting getNewInstanceApi() {
        return new LocalMeetingService();
    }

    public static LocalApiPlace getNewInstancePlaceApi() {
        return new PlaceServiceLocal();
    }
}
