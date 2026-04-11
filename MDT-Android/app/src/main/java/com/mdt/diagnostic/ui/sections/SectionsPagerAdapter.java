package com.mdt.diagnostic.ui.sections;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SectionsPagerAdapter extends FragmentStateAdapter {

  public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
    super(fragmentActivity);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
    if (position == 1) {
      return new InfoFragment();
    }
    if (position == 2) {
      return new SensorsFragment();
    }
    if (position == 3) {
      return new StorageFragment();
    }
    return new HomeFragment();
  }

  @Override
  public int getItemCount() {
    return 4;
  }
}
