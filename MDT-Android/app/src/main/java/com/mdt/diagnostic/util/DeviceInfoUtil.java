package com.mdt.diagnostic.util;

import android.content.Context;
import android.os.BatteryManager;
import android.os.Build;

import com.mdt.diagnostic.model.DiagnosticItem;

import java.util.ArrayList;
import java.util.List;

public final class DeviceInfoUtil {

  private DeviceInfoUtil() {
  }

  public static List<DiagnosticItem> getOverview(Context context) {
    List<DiagnosticItem> items = new ArrayList<>();
    items.add(new DiagnosticItem("Device", Build.MANUFACTURER + " " + Build.MODEL, "OK"));
    items.add(
        new DiagnosticItem("Android Version", Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + ")", "OK"));
    items.add(new DiagnosticItem("Build Type", Build.TYPE, "OK"));
    items.add(new DiagnosticItem("Battery", getBatteryLevel(context) + "%", "CHECK"));
    return items;
  }

  public static List<DiagnosticItem> getDetailedInfo(Context context) {
    List<DiagnosticItem> items = new ArrayList<>();
    items.add(new DiagnosticItem("Brand", Build.BRAND, "OK"));
    items.add(new DiagnosticItem("Model", Build.MODEL, "OK"));
    items.add(new DiagnosticItem("Device", Build.DEVICE, "OK"));
    items.add(new DiagnosticItem("Product", Build.PRODUCT, "OK"));
    items.add(new DiagnosticItem("Hardware", Build.HARDWARE, "OK"));
    items.add(new DiagnosticItem("Board", Build.BOARD, "OK"));
    items.add(new DiagnosticItem("Kernel", System.getProperty("os.version", "Unknown"), "OK"));
    items.add(new DiagnosticItem("IMEI", "Restricted on modern Android; use privileged APIs only", "INFO"));
    items.add(new DiagnosticItem("Battery", getBatteryLevel(context) + "%", "CHECK"));
    return items;
  }

  private static int getBatteryLevel(Context context) {
    BatteryManager bm = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
    if (bm == null) {
      return -1;
    }
    return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
  }
}
