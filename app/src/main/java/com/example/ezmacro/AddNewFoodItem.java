package com.example.ezmacro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class AddNewFoodItem extends AppCompatActivity
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
	private Button saveButton;

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

		saveButton = findViewById(R.id.saveButton);
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
	}

	private void saveFoodItem()
	{
		String name = nameTextInput.getText().toString();
		String description = descriptionTextInput.getText().toString();
		boolean isFavorite = favoriteSwitch.isChecked();
		float weight = Float.parseFloat(weightTextInput.getText().toString());
		float servings = Float.parseFloat(servingsTextInput.getText().toString());
		String barcode = barcodeTextInput.getText().toString();
		float energy = Float.parseFloat(energyTextInput.getText().toString());
		float protein = Float.parseFloat(proteinTextInput.getText().toString());
		float fat = Float.parseFloat(fatTextInput.getText().toString());
		float carb = Float.parseFloat(carbTextInput.getText().toString());

		Nutrition nutrition = new Nutrition(energy, protein, fat, carb);
		FoodItem foodItem = new FoodItem(name, description, nutrition, barcode, servings, weight, isFavorite);
//		foodItemViewModel.insert(foodItem);
		finish();
	}
}