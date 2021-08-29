package com.transienttetra.ezmacro.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ezmacro.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.transienttetra.ezmacro.fragments.FoodDbFragment;
import com.transienttetra.ezmacro.fragments.HomeFragment;
import com.transienttetra.ezmacro.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
		bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
		{
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item)
			{
				Fragment selectedFragment = null;

				switch (item.getItemId())
				{
					case R.id.nav_home:
						selectedFragment = new HomeFragment();
						break;
					case R.id.nav_food_db:
						selectedFragment = new FoodDbFragment();
						break;
					case R.id.nav_settings:
						selectedFragment = new SettingsFragment();
						break;
				}

				getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();
				return true;
			}
		});
		getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();
	}
}