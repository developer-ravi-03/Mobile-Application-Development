package com.mdt.diagnostic.ui.sections;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mdt.diagnostic.databinding.FragmentStorageBinding;
import com.mdt.diagnostic.model.DiagnosticItem;
import com.mdt.diagnostic.ui.common.DiagnosticAdapter;
import com.mdt.diagnostic.util.DeviceInfoUtil;
import com.mdt.diagnostic.util.HardwareInfoUtil;
import com.mdt.diagnostic.util.ReportUtil;
import com.mdt.diagnostic.util.StorageUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StorageFragment extends Fragment {

  private FragmentStorageBinding binding;
  private DiagnosticAdapter adapter;
  private String callLogsSummary = "No data";

  private final ActivityResultLauncher<String> callLogPermissionLauncher = registerForActivityResult(
      new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
          loadCallLogs();
        } else {
          binding.tvCallLogs.setText("Call log permission denied");
        }
      });

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentStorageBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter = new DiagnosticAdapter();
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    binding.recyclerView.setAdapter(adapter);

    adapter.submitList(StorageUtil.getStorageInfo());

    binding.btnLoadCallLogs.setOnClickListener(v -> checkPermissionAndLoadCallLogs());
    binding.btnGenerateReport.setOnClickListener(v -> generateReport());
  }

  private void checkPermissionAndLoadCallLogs() {
    if (ContextCompat.checkSelfPermission(requireContext(),
        Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
      loadCallLogs();
    } else {
      callLogPermissionLauncher.launch(Manifest.permission.READ_CALL_LOG);
    }
  }

  private void loadCallLogs() {
    StringBuilder sb = new StringBuilder();
    Cursor cursor = requireContext().getContentResolver().query(
        CallLog.Calls.CONTENT_URI,
        null,
        null,
        null,
        CallLog.Calls.DATE + " DESC");

    if (cursor == null) {
      binding.tvCallLogs.setText("Unable to access call logs");
      callLogsSummary = "Unable to access call logs";
      return;
    }

    int numberIndex = cursor.getColumnIndex(CallLog.Calls.NUMBER);
    int typeIndex = cursor.getColumnIndex(CallLog.Calls.TYPE);
    int count = 0;

    while (cursor.moveToNext() && count < 8) {
      String number = numberIndex >= 0 ? cursor.getString(numberIndex) : "Unknown";
      int type = typeIndex >= 0 ? cursor.getInt(typeIndex) : -1;
      String typeLabel = resolveCallType(type);
      sb.append(typeLabel).append(" - ").append(number).append("\n");
      count++;
    }
    cursor.close();

    if (count == 0) {
      sb.append("No call logs found");
    }

    callLogsSummary = sb.toString();
    binding.tvCallLogs.setText(callLogsSummary);
  }

  private String resolveCallType(int type) {
    if (type == CallLog.Calls.INCOMING_TYPE) {
      return "Incoming";
    }
    if (type == CallLog.Calls.OUTGOING_TYPE) {
      return "Outgoing";
    }
    if (type == CallLog.Calls.MISSED_TYPE) {
      return "Missed";
    }
    return "Other";
  }

  private void generateReport() {
    List<DiagnosticItem> overview = DeviceInfoUtil.getOverview(requireContext());
    List<DiagnosticItem> detail = DeviceInfoUtil.getDetailedInfo(requireContext());
    List<DiagnosticItem> hardware = HardwareInfoUtil.getHardwareAndNetwork(requireContext());
    List<DiagnosticItem> storage = StorageUtil.getStorageInfo();

    String report = ReportUtil.buildReport(overview, detail, hardware, storage, callLogsSummary);
    try {
      ReportUtil.shareReport(requireContext(), report);
      Toast.makeText(requireContext(), "Report generated and ready to share", Toast.LENGTH_SHORT).show();
    } catch (IOException e) {
      Toast.makeText(requireContext(), "Report generation failed", Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
