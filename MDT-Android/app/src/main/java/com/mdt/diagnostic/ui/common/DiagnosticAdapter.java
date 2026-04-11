package com.mdt.diagnostic.ui.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.mdt.diagnostic.R;
import com.mdt.diagnostic.model.DiagnosticItem;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticAdapter extends RecyclerView.Adapter<DiagnosticAdapter.DiagnosticViewHolder> {

  private final List<DiagnosticItem> items = new ArrayList<>();

  public void submitList(List<DiagnosticItem> newItems) {
    items.clear();
    if (newItems != null) {
      items.addAll(newItems);
    }
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public DiagnosticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diagnostic, parent, false);
    return new DiagnosticViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DiagnosticViewHolder holder, int position) {
    DiagnosticItem item = items.get(position);
    holder.tvTitle.setText(item.getTitle());
    holder.tvValue.setText(item.getValue());
    holder.chipStatus.setText(item.getStatus());
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  static class DiagnosticViewHolder extends RecyclerView.ViewHolder {
    final TextView tvTitle;
    final TextView tvValue;
    final Chip chipStatus;

    DiagnosticViewHolder(@NonNull View itemView) {
      super(itemView);
      tvTitle = itemView.findViewById(R.id.tvTitle);
      tvValue = itemView.findViewById(R.id.tvValue);
      chipStatus = itemView.findViewById(R.id.chipStatus);
    }
  }
}
