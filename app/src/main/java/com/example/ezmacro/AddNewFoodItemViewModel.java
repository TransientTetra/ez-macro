package com.example.ezmacro;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AddNewFoodItemViewModel extends AndroidViewModel
{
	FoodItemRepository repository;
	public AddNewFoodItemViewModel(@NonNull Application application)
	{
		super(application);
		repository = new FoodItemRepository(application);
	}

	public void insert(FoodItem foodItem)
	{
		repository.insert(foodItem);
	}
}
