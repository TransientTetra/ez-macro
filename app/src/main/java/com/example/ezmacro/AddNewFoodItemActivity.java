package com.example.ezmacro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class AddNewFoodItemActivity extends AppCompatActivity
{
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

	private AddNewFoodItemViewModel viewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_food_item);
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

		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
		setTitle(R.string.addNewFoodItemTitle);

		viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(AddNewFoodItemViewModel.class);
	}

	private float getValue(EditText editText)
	{
		float ret;
		try
		{
			ret = Float.parseFloat(editText.getText().toString());
		} catch (Exception e)
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
		float weight = getValue(weightTextInput);
		float servings = getValue(servingsTextInput);
		String barcode = barcodeTextInput.getText().toString();
		float energy = getValue(energyTextInput);
		float protein = getValue(proteinTextInput);
		float fat = getValue(fatTextInput);
		float carb = getValue(carbTextInput);

		Nutrition nutrition = new Nutrition(energy, protein, fat, carb);
		FoodItem foodItem = new FoodItem(name, description, nutrition, barcode, servings, weight, isFavorite);
		viewModel.insert(foodItem);
		finish();
	}
}