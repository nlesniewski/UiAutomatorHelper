package it.lesniewski.android.uiautomatorhelper;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

/**
 * Created by nlesniewski on 01.02.2017.
 */

public class DeviceProvider {
    public static UiDevice getUiDevice() {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }
}
