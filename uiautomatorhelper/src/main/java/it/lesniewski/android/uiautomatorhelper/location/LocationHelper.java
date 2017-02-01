package it.lesniewski.android.uiautomatorhelper.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

import it.lesniewski.android.uiautomatorhelper.exceptions.UiAutomatorHelperException;

import static it.lesniewski.android.uiautomatorhelper.AdbShellHelper.executeShellCommand;

/**
 * Created by nlesniewski on 01.02.2017.
 */

public class LocationHelper {

    private static final String SETTINGS_MANAGE_LOCATION_PROVIDERS_CMD = "settings %s secure location_providers_allowed %s";

    private static final String TEST_LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;

    public static void setLocationState(UiDevice uiDevice, LocationState locationState) throws UiAutomatorHelperException {
        executeShellCommand(uiDevice, SETTINGS_MANAGE_LOCATION_PROVIDERS_CMD, "put", locationState.toString());
    }

    //todo return enum?
    @SuppressWarnings("MissingPermission")
    public static Location getCurrentLocation() throws UiAutomatorHelperException {
        return getGpsLocationManager().getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    public static LocationManager getGpsLocationManager() { //todo rename to getLocationManager
        return (LocationManager) InstrumentationRegistry.getContext().getSystemService(Context.LOCATION_SERVICE);
    }

    public static void clearTestLocationProvider() { //todo remove?
        getGpsLocationManager().clearTestProviderLocation(TEST_LOCATION_PROVIDER);
        getGpsLocationManager().clearTestProviderEnabled(TEST_LOCATION_PROVIDER);
        getGpsLocationManager().clearTestProviderStatus(TEST_LOCATION_PROVIDER);
    }

    public static void setMockLocation(Location location) throws UiAutomatorHelperException {
        LocationManager lm = getGpsLocationManager();

        if (TEST_LOCATION_PROVIDER == null) {
            throw new UiAutomatorHelperException("TEST_LOCATION_PROVIDER == null");
        }

        lm.addTestProvider(TEST_LOCATION_PROVIDER, false, false, false, false, true, true, true, 0, 5);
        lm.setTestProviderEnabled(TEST_LOCATION_PROVIDER, true);

        lm.setTestProviderLocation(TEST_LOCATION_PROVIDER, location);
    }
}
