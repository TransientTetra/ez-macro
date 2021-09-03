package com.transienttetra.ezmacro.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.transienttetra.ezmacro.viewmodels.AddFoodItemToDayLogViewModel;
import com.transienttetra.ezmacro.ui.adapters.FoodItemAdapter;
import com.transienttetra.ezmacro.R;
import com.transienttetra.ezmacro.ui.dialogs.WeightDialog;
import com.transienttetra.ezmacro.entities.DayLog;
import com.transienttetra.ezmacro.entities.FoodItem;

import java.time.LocalDate;
import java.util.List;


public class AddFoodItemToDayLogActivity extends AppCompatActivity
{
	public static final String EXTRA_DAY_LOG_ID = "com.transienttetra.ezmacro.ui.activities.EXTRA_DAY_LOG_ID";

	private AddFoodItemToDayLogViewModel viewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_food_item_to_day_log);

		FloatingActionButton addFoodItemButton = findViewById(R.id.addFoodItemButton);
		addFoodItemButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(AddFoodItemToDayLogActivity.this, AddEditFoodItemActivity.class);
				startActivity(intent);
			}
		});

		RecyclerView foodItemsView = findViewById(R.id.foodItemsView);
		FoodItemAdapter foodItemAdapter = new FoodItemAdapter();
		foodItemsView.setAdapter(foodItemAdapter);
		foodItemsView.setLayoutManager(new LinearLayoutManager(AddFoodItemToDayLogActivity.this));
		foodItemsView.addItemDecoration(new DividerItemDecoration(AddFoodItemToDayLogActivity.this, DividerItemDecoration.VERTICAL));
		viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AddFoodItemToDayLogViewModel.class);
		viewModel.getAllFoodItems().observe(this, new Observer<List<FoodItem>>()
		{
			@Override
			public void onChanged(List<FoodItem> foodItems)
			{
				foodItemAdapter.submitList(foodItems);
			}
		});

		foodItemAdapter.setOnItemClickListener(new FoodItemAdapter.OnItemClickListener()
		{
			@Override
			public void onItemClick(FoodItem foodItem)
			{
				// attach food item to daylog
				WeightDialog weightDialog = new WeightDialog(AddFoodItemToDayLogActivity.this,
					new WeightDialog.OnSaveListener()
					{
						@Override
						public void onSave(WeightDialog weightDialog)
						{
							LocalDate dayLogDate = (LocalDate) getIntent().getSerializableExtra(EXTRA_DAY_LOG_ID);
							viewModel.attach(new DayLog(dayLogDate), foodItem, weightDialog.getWeight());
							finish();
						}
					});
				weightDialog.show();
			}
		});
	}
}