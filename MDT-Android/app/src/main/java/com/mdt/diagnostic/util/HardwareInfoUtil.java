package com.mdt.diagnostic.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.mdt.diagnostic.model.DiagnosticItem;

import java.util.ArrayList;
import java.util.List;

public final class HardwareInfoUtil {

  private HardwareInfoUtil() {
  }

  public static List<DiagnosticItem> getHardwareAndNetwork(Context context) {
    List<DiagnosticItem> items = new ArrayList<>();

    PackageManager pm = context.getPackageManager();
    boolean hasCamera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    items.add(new DiagnosticItem("Camera", hasCamera ? "Available" : "Not Available", hasCamera ? "OK" : "WARN"));

    AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    int volume = audioManager != null ? audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) : -1;
    items.add(new DiagnosticItem("Sound", volume >= 0 ? "Media volume level: " + volume : "Unknown", "INFO"));

    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics dm = new DisplayMetrics();
    if (windowManager != null && windowManager.getDefaultDisplay() != null) {
      windowManager.getDefaultDisplay().getMetrics(dm);
      String displaySummary = dm.widthPixels + " x " + dm.heightPixels + " @ " + dm.densityDpi + " dpi";
      items.add(new DiagnosticItem("Display", displaySummary, "OK"));
    } else {
      items.add(new DiagnosticItem("Display", "Unavailable", "WARN"));
    }

    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    String networkType = "Disconnected";
    if (cm != null) {
      Network network = cm.getActiveNetwork();
      NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
      if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
          networkType = "Wi-Fi";
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
          networkType = "Mobile Data";
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
          networkType = "Ethernet";
        } else {
          networkType = "Connected";
        }
      }
    }
    items.add(new DiagnosticItem("Network", networkType, "INFO"));

    return items;
  }
}
