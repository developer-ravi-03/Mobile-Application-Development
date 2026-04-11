package com.mdt.diagnostic.ui.sections;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mdt.diagnostic.databinding.FragmentInfoBinding;
import com.mdt.diagnostic.model.DiagnosticItem;
import com.mdt.diagnostic.ui.common.DiagnosticAdapter;
import com.mdt.diagnostic.util.DeviceInfoUtil;
import com.mdt.diagnostic.util.HardwareInfoUtil;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment {

  private FragmentInfoBinding binding;
  private DiagnosticAdapter adapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentInfoBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter = new DiagnosticAdapter();
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    binding.recyclerView.setAdapter(adapter);

    binding.btnRefreshInfo.setOnClickListener(v -> loadInfo());
    loadInfo();
  }

  private void loadInfo() {
    List<DiagnosticItem> data = new ArrayList<>();
    data.addAll(DeviceInfoUtil.getDetailedInfo(requireContext()));
    data.addAll(HardwareInfoUtil.getHardwareAndNetwork(requireContext()));
    adapter.submitList(data);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
