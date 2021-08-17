package com.example.ezmacro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ezmacro.util.EnergyConverter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
	private ProgressBar energyProgressBar;
	private TextView energyPercent;
	
	private ProgressBar proteinProgressBar;
	private TextView proteinPercent;

	private ProgressBar fatProgressBar;
	private TextView fatPercent;

	private ProgressBar carbProgressBar;
	private TextView carbPercent;

	private MainActivityViewModel mainActivityViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		energyProgressBar = findViewById(R.id.energyProgressBar);
		energyPercent = findViewById(R.id.energyProgressPercent);
		proteinProgressBar = findViewById(R.id.proteinProgressBar);
		proteinPercent = findViewById(R.id.proteinProgressPercent);
		fatProgressBar = findViewById(R.id.fatProgressBar);
		fatPercent = findViewById(R.id.fatProgressPercent);
		carbProgressBar = findViewById(R.id.carbProgressBar);
		carbPercent = findViewById(R.id.carbProgressPercent);

		// todo temporary
		Nutrition goal = new Nutrition(EnergyConverter.kcalToJoule(3000), 200, 90, 250);

		FloatingActionButton addFoodItemButton = findViewById(R.id.addFoodItemButton);
		addFoodItemButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, AddNewFoodItemActivity.class);
				startActivity(intent);
			}
		});

		RecyclerView foodItemsView = findViewById(R.id.foodItemsView);
		FoodItemAdapter foodItemAdapter = new FoodItemAdapter();
		foodItemsView.setAdapter(foodItemAdapter);
		foodItemsView.setLayoutManager(new LinearLayoutManager(this));
		foodItemsView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

		mainActivityViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MainActivityViewModel.class);
		mainActivityViewModel.getAll().observe(this, new Observer<List<FoodItem>>()
		{
			@Override
			public void onChanged(List<FoodItem> foodItems)
			{
				Nutrition progress = new Nutrition();
				for (FoodItem foodItem : foodItems)
				{
					progress.add(foodItem.getNutrition());
				}
				setProgress(goal, progress);
				foodItemAdapter.setFoodItemList(foodItems);
			}
		});
	}

	private void setProgress(Nutrition target, Nutrition current)
	{
		setEnergyProgress(Math.round(current.getEnergy() / target.getEnergy() * 100));
		setProteinProgress(Math.round(current.getProtein() / target.getProtein() * 100));
		setFatProgress(Math.round(current.getFats() / target.getFats() * 100));
		setCarbProgress(Math.round(current.getCarbohydrates() / target.getCarbohydrates() * 100));
	}

	private void setSingleProgress(int percent, ProgressBar progressBar, TextView textView)
	{
		// todo what when over 100%?
		percent = Math.max(percent, 0);
		progressBar.setProgress(percent);
		textView.setText(percent + "%");
	}

	public void setEnergyProgress(int percent)
	{
		setSingleProgress(percent, energyProgressBar, energyPercent);
	}

	public void setProteinProgress(int percent)
	{
		setSingleProgress(percent, proteinProgressBar, proteinPercent);
	}

	public void setFatProgress(int percent)
	{
		setSingleProgress(percent, fatProgressBar, fatPercent);
	}

	public void setCarbProgress(int percent)
	{
		setSingleProgress(percent, carbProgressBar, carbPercent);
	}
}