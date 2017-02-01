package it.lesniewski.android.uiautomatorhelper;

import android.support.test.uiautomator.UiDevice;

import it.lesniewski.android.uiautomatorhelper.exceptions.UiAutomatorHelperException;

/**
 * Created by nlesniewski on 01.02.2017.
 */

public class AdbShellHelper {
    private static final String PM_CLEAR_CMD = "pm clear %s";
    private static final String PM_GRANT_PERMISSION_CMD = "pm grant %s %s";
    private static final String AM_FORCE_STOP_CMD = "am force-stop %s";

    /**
     * @param uiDevice
     * @param packageName
     * @param permission use values available in Manifest.permission
     * @throws UiAutomatorHelperException
     */
    public static void grantPermission(UiDevice uiDevice, String packageName, String permission) throws UiAutomatorHelperException {
        executeShellCommand(uiDevice, PM_GRANT_PERMISSION_CMD, packageName, permission);
    }

    public static void clearPackageData(UiDevice uiDevice, String packageName) throws UiAutomatorHelperException {
        executeShellCommand(uiDevice, PM_CLEAR_CMD, packageName);
    }

    public static void forceStopApplication(UiDevice uiDevice, String packageName) throws UiAutomatorHelperException {
        executeShellCommand(uiDevice, AM_FORCE_STOP_CMD, packageName);
    }

    public static String executeShellCommand(UiDevice uiDevice, String cmd, String... arguments) throws UiAutomatorHelperException {
        String preparedCmd = prepareCommand(cmd, arguments);
        try {
            return uiDevice.executeShellCommand(preparedCmd);
        } catch (Exception e) {
            throw new UiAutomatorHelperException("executeShellCommand - could not execute command.", e);
        }
    }

    private static String prepareCommand(String commandTemplate, Object... arguments) {
        return String.format(commandTemplate, arguments);
    }
}
