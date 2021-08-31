package com.transienttetra.ezmacro.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.transienttetra.ezmacro.AddFoodItemToDayLogViewModel;
import com.transienttetra.ezmacro.FoodDbFragmentViewModel;
import com.transienttetra.ezmacro.FoodItemAdapter;
import com.transienttetra.ezmacro.R;
import com.transienttetra.ezmacro.entities.DayLog;
import com.transienttetra.ezmacro.entities.FoodItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AddFoodItemToDayLogActivity extends AppCompatActivity
{
	public static final String EXTRA_DAY_LOG_ID = "com.transienttetra.ezmacro.activities.EXTRA_DAY_LOG_ID";

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
				weightDialog(foodItem);
			}
		});
	}

	private void weightDialog(FoodItem foodItem)
	{
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		final EditText editText = new EditText(AddFoodItemToDayLogActivity.this);
		editText.setHint(R.string.weight_dialog_hint);
		editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER);
		dialogBuilder.setTitle(R.string.weight_dialog_title);
		dialogBuilder.setView(editText);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(editText);
		dialogBuilder.setView(layout);
		dialogBuilder.setPositiveButton(R.string.weight_dialog_save, (dialog, which) ->
		{
			LocalDate dayLogDate = (LocalDate) getIntent().getSerializableExtra(EXTRA_DAY_LOG_ID);
			float weight;
			try
			{
				weight = Float.parseFloat(editText.getText().toString());
			}
			catch (Exception e)
			{
				weight = 0;
			};
			viewModel.attach(new DayLog(dayLogDate), foodItem, weight);
			finish();
		});
		dialogBuilder.show();
	}
}