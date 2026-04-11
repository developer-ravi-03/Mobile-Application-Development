package com.mdt.diagnostic;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayoutMediator;
import com.mdt.diagnostic.databinding.ActivityMainBinding;
import com.mdt.diagnostic.ui.sections.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    setSupportActionBar(binding.toolbar);

    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this);
    binding.viewPager.setAdapter(sectionsPagerAdapter);
    binding.viewPager.setOffscreenPageLimit(4);

    String[] tabTitles = { "Dashboard", "Info", "Sensors", "Storage" };
    new TabLayoutMediator(binding.tabLayout, binding.viewPager,
        (tab, position) -> tab.setText(tabTitles[position])).attach();
  }
}
