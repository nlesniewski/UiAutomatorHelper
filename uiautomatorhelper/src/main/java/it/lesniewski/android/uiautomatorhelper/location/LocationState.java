package it.lesniewski.android.uiautomatorhelper.location;

/**
 * Created by nlesniewski on 01.02.2017.
 */

public enum LocationState {
    GpsOnly("gps"),
    BatterySaving("wifi,network"),
    HighAccuracy("gps,wifi,network"),
    Disabled("''");

    private final String state;

    LocationState(String state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return state;
    }
}