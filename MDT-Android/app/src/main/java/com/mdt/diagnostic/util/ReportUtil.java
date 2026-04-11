package com.mdt.diagnostic.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;

import com.mdt.diagnostic.model.DiagnosticItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public final class ReportUtil {

  private ReportUtil() {
  }

  public static String buildReport(List<DiagnosticItem> overview,
      List<DiagnosticItem> details,
      List<DiagnosticItem> hardware,
      List<DiagnosticItem> storage,
      String callLogsSummary) {
    StringBuilder sb = new StringBuilder();
    sb.append("MDT - Mobile Diagnostic Report\n");
    sb.append("Generated: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date()))
        .append("\n\n");

    appendSection(sb, "Dashboard", overview);
    appendSection(sb, "Mobile Info", details);
    appendSection(sb, "Hardware & Network", hardware);
    appendSection(sb, "Storage & Memory", storage);

    sb.append("Call Logs\n");
    sb.append("------------------------------\n");
    sb.append(callLogsSummary == null || callLogsSummary.isEmpty() ? "No call log data\n" : callLogsSummary)
        .append("\n\n");

    return sb.toString();
  }

  public static void shareReport(Context context, String reportText) throws IOException {
    File reportFile = new File(context.getCacheDir(), "mdt_report.txt");
    try (FileOutputStream fos = new FileOutputStream(reportFile)) {
      fos.write(reportText.getBytes(StandardCharsets.UTF_8));
    }

    Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", reportFile);
    Intent shareIntent = new Intent(Intent.ACTION_SEND);
    shareIntent.setType("text/plain");
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MDT Diagnostic Report");
    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

    context.startActivity(Intent.createChooser(shareIntent, "Share MDT Report"));
  }

  private static void appendSection(StringBuilder sb, String title, List<DiagnosticItem> items) {
    sb.append(title).append("\n");
    sb.append("------------------------------\n");
    if (items == null || items.isEmpty()) {
      sb.append("No data\n\n");
      return;
    }
    for (DiagnosticItem item : items) {
      sb.append("- ")
          .append(item.getTitle())
          .append(": ")
          .append(item.getValue())
          .append(" [")
          .append(item.getStatus())
          .append("]\n");
    }
    sb.append("\n");
  }
}
