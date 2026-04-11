package com.mdt.diagnostic.ui.sections;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mdt.diagnostic.databinding.FragmentSensorsBinding;
import com.mdt.diagnostic.ui.common.DiagnosticAdapter;
import com.mdt.diagnostic.util.SensorMonitor;

public class SensorsFragment extends Fragment {

  private FragmentSensorsBinding binding;
  private DiagnosticAdapter adapter;
  private SensorMonitor sensorMonitor;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentSensorsBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter = new DiagnosticAdapter();
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    binding.recyclerView.setAdapter(adapter);

    sensorMonitor = new SensorMonitor(requireContext(), data -> {
      if (isAdded()) {
        adapter.submitList(data);
      }
    });
  }

  @Override
  public void onResume() {
    super.onResume();
    if (sensorMonitor != null) {
      sensorMonitor.start();
    }
  }

  @Override
  public void onPause() {
    if (sensorMonitor != null) {
      sensorMonitor.stop();
    }
    super.onPause();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
