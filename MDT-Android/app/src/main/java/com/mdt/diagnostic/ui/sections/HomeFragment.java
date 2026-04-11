package com.mdt.diagnostic.ui.sections;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mdt.diagnostic.databinding.FragmentHomeBinding;
import com.mdt.diagnostic.ui.common.DiagnosticAdapter;
import com.mdt.diagnostic.util.DeviceInfoUtil;

public class HomeFragment extends Fragment {

  private FragmentHomeBinding binding;
  private DiagnosticAdapter adapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter = new DiagnosticAdapter();
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    binding.recyclerView.setAdapter(adapter);

    adapter.submitList(DeviceInfoUtil.getOverview(requireContext()));
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
