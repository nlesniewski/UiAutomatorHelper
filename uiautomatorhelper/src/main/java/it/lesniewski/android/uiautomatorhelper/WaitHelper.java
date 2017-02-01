package it.lesniewski.android.uiautomatorhelper;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

/**
 * Created by nlesniewski on 01.02.2017.
 */

public class WaitHelper {
    public static void waitForPackageToAppearOnScreen(UiDevice uiDevice, String packageName, int launchTimeout) {
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), launchTimeout);
    }
}
