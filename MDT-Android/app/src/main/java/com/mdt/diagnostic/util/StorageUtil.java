package com.mdt.diagnostic.util;

import android.os.Environment;
import android.os.StatFs;

import com.mdt.diagnostic.model.DiagnosticItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class StorageUtil {

  private StorageUtil() {
  }

  public static List<DiagnosticItem> getStorageInfo() {
    List<DiagnosticItem> items = new ArrayList<>();

    File internalPath = Environment.getDataDirectory();
    StatFs internalStat = new StatFs(internalPath.getPath());

    long internalTotal = internalStat.getTotalBytes();
    long internalFree = internalStat.getAvailableBytes();
    items.add(new DiagnosticItem("Internal Total", formatBytes(internalTotal), "OK"));
    items.add(new DiagnosticItem("Internal Free", formatBytes(internalFree),
        internalFree > (1024L * 1024L * 1024L) ? "OK" : "WARN"));

    File externalPath = Environment.getExternalStorageDirectory();
    StatFs externalStat = new StatFs(externalPath.getPath());
    long externalTotal = externalStat.getTotalBytes();
    long externalFree = externalStat.getAvailableBytes();
    items.add(new DiagnosticItem("External Total", formatBytes(externalTotal), "INFO"));
    items.add(new DiagnosticItem("External Free", formatBytes(externalFree), "INFO"));

    return items;
  }

  private static String formatBytes(long bytes) {
    double gb = bytes / (1024.0 * 1024.0 * 1024.0);
    return String.format("%.2f GB", gb);
  }
}
