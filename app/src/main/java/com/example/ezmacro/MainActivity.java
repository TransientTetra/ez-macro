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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
	private ProgressBar caloriesProgressBar;
	private TextView caloriesPercent;
	
	private ProgressBar proteinProgressBar;
	private TextView proteinPercent;

	private ProgressBar fatProgressBar;
	private TextView fatPercent;

	private ProgressBar carbProgressBar;
	private TextView carbPercent;

	private FoodItemViewModel foodItemViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		caloriesProgressBar = findViewById(R.id.caloriesProgressBar);
		caloriesPercent = findViewById(R.id.caloriesProgressPercent);
		proteinProgressBar = findViewById(R.id.proteinProgressBar);
		proteinPercent = findViewById(R.id.proteinProgressPercent);
		fatProgressBar = findViewById(R.id.fatProgressBar);
		fatPercent = findViewById(R.id.fatProgressPercent);
		carbProgressBar = findViewById(R.id.carbProgressBar);
		carbPercent = findViewById(R.id.carbProgressPercent);

		setCalorieProgress(39);
		setProteinProgress(50);
		setFatProgress(80);
		setCarbProgress(120);

		FloatingActionButton addFoodItemButton = findViewById(R.id.addFoodItemButton);
		addFoodItemButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, AddNewFoodItem.class);
				startActivity(intent);
			}
		});

		RecyclerView foodItemsView = findViewById(R.id.foodItemsView);
		FoodItemAdapter foodItemAdapter = new FoodItemAdapter();
		foodItemsView.setAdapter(foodItemAdapter);
		foodItemsView.setLayoutManager(new LinearLayoutManager(this));
		foodItemsView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

		foodItemViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(FoodItemViewModel.class);
		foodItemViewModel.getAll().observe(this, new Observer<List<FoodItem>>()
		{
			@Override
			public void onChanged(List<FoodItem> foodItems)
			{
				foodItemAdapter.setFoodItemList(foodItems);
			}
		});
	}

	private void setSingleProgress(int percent, ProgressBar progressBar, TextView textView)
	{
		// todo what when over 100%?
		percent = Math.max(percent, 0);
		progressBar.setProgress(percent);
		textView.setText(progressBar.getProgress() + "%");
	}

	public void setCalorieProgress(int percent)
	{
		setSingleProgress(percent, caloriesProgressBar, caloriesPercent);
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