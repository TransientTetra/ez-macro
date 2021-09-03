package com.transienttetra.ezmacro.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.transienttetra.ezmacro.viewmodels.AddEditFoodItemViewModel;
import com.transienttetra.ezmacro.R;
import com.transienttetra.ezmacro.entities.Nutrition;
import com.transienttetra.ezmacro.entities.FoodItem;
import com.transienttetra.ezmacro.util.EnergyConverter;

public class AddEditFoodItemActivity extends AppCompatActivity
{
	public static final String EXTRA_ID = "com.transienttetra.ezmacro.ui.activities.EXTRA_ID";

	private EditText nameTextInput;
	private EditText descriptionTextInput;
	private Switch favoriteSwitch;
	private EditText weightTextInput;
	private EditText servingsTextInput;
	private EditText barcodeTextInput;
	private EditText energyTextInput;
	private EditText proteinTextInput;
	private EditText fatTextInput;
	private EditText carbTextInput;

	private AddEditFoodItemViewModel viewModel;
	private Intent intent;
	private int foodItemId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_edit_food_item);
		nameTextInput = findViewById(R.id.nameTextInput);
		descriptionTextInput = findViewById(R.id.descriptionTextInput);
		favoriteSwitch = findViewById(R.id.favoriteSwitch);
		weightTextInput = findViewById(R.id.weightTextInput);
		servingsTextInput = findViewById(R.id.servingsTextInput);
		barcodeTextInput = findViewById(R.id.barcodeTextInput);
		energyTextInput = findViewById(R.id.energyTextInput);
		proteinTextInput = findViewById(R.id.proteinTextInput);
		fatTextInput = findViewById(R.id.fatTextInput);
		carbTextInput = findViewById(R.id.carbTextInput);

		Button saveButton = findViewById(R.id.saveButton);
		saveButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				saveFoodItem();
			}
		});

		viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(AddEditFoodItemViewModel.class);

		intent = getIntent();
		if (intent.hasExtra(EXTRA_ID))
		{
			setTitle(R.string.editFoodItemTitle);
			foodItemId = intent.getIntExtra(EXTRA_ID, -1);
			if (foodItemId != -1)
			{
				viewModel.get(foodItemId).observe(this, new Observer<FoodItem>()
				{
					@Override
					public void onChanged(FoodItem foodItem)
					{
						setAllFields(foodItem);
					}
				});
			}
		}
		else
		{
			setTitle(R.string.addNewFoodItemTitle);
		}
	}

	private void setAllFields(FoodItem foodItem)
	{
		favoriteSwitch.setChecked(foodItem.isFavorite());
		nameTextInput.setText(foodItem.getName());
		descriptionTextInput.setText(foodItem.getDescription());
		weightTextInput.setText(Float.toString(foodItem.getWeight()));
		servingsTextInput.setText(Float.toString(foodItem.getServings()));
		barcodeTextInput.setText(foodItem.getBarcode());
		// todo temporary fixed input in kcal
		energyTextInput.setText(Float.toString(EnergyConverter.jouleToKcal(foodItem.getNutrition().getEnergy())));
		proteinTextInput.setText(Float.toString(foodItem.getNutrition().getProtein()));
		fatTextInput.setText(Float.toString(foodItem.getNutrition().getFats()));
		carbTextInput.setText(Float.toString(foodItem.getNutrition().getCarbohydrates()));
	}

	private float getFieldValue(EditText editText)
	{
		float ret;
		try
		{
			ret = Float.parseFloat(editText.getText().toString());
		}
		catch (Exception e)
		{
			ret = 0;
		}
		return ret;
	}

	private void saveFoodItem()
	{
		boolean isFavorite = favoriteSwitch.isChecked();

		String name = nameTextInput.getText().toString();
		String description = descriptionTextInput.getText().toString();
		float weight = getFieldValue(weightTextInput);
		float servings = getFieldValue(servingsTextInput);
		String barcode = barcodeTextInput.getText().toString();
		// todo temporary fixed input in kcal
		float energy = EnergyConverter.kcalToJoule(getFieldValue(energyTextInput));
		float protein = getFieldValue(proteinTextInput);
		float fat = getFieldValue(fatTextInput);
		float carb = getFieldValue(carbTextInput);

		Nutrition nutrition = new Nutrition(energy, protein, fat, carb);
		FoodItem foodItem = new FoodItem(name, description, nutrition, barcode, servings, weight, isFavorite);
		if (intent.hasExtra(EXTRA_ID) && foodItemId != -1)
		{
			foodItem.setFoodItemId(foodItemId);
			viewModel.update(foodItem);
		}
		else
			viewModel.insert(foodItem);
		finish();
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				finish();
				break;
		}
		return true;
	}
}