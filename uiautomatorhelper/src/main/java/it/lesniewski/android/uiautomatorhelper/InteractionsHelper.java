package it.lesniewski.android.uiautomatorhelper;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

/**
 * Created by nlesniewski on 01.02.2017.
 */

public class InteractionsHelper {
    public static void unlockDevice(UiDevice uiDevice, boolean withSwipe) throws UiAutomatorHelperException {
        if (uiDevice == null) {
            throw new NullPointerException();
        }

        try {
            uiDevice.wakeUp();
        } catch (RemoteException e) {
            throw new UiAutomatorHelperException("unlockDevice - Could not wake up device.", e);
        }

        if (withSwipe) {//todo think about taking into account screen size
            uiDevice.swipe(100, 2000, 100, 500, 10);
        }
    }

    public static void unlockDevice(UiDevice mDevice) throws UiAutomatorHelperException {
        unlockDevice(mDevice, false);
    }

    /**
     * Uses package manager to find the package name of the device launcher. Usually this package
     * is "com.android.launcher" but can be different at times. This is a generic solution which
     * works on all platforms.`
     */
    public static String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }
}
