package com.transienttetra.ezmacro.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.transienttetra.ezmacro.repositories.FoodItemRepository;
import com.transienttetra.ezmacro.entities.FoodItem;

public class AddEditFoodItemViewModel extends AndroidViewModel
{
	FoodItemRepository repository;
	public AddEditFoodItemViewModel(@NonNull Application application)
	{
		super(application);
		repository = new FoodItemRepository(application);
	}

	public void insert(FoodItem foodItem)
	{
		repository.insert(foodItem);
	}

	public void update(FoodItem foodItem)
	{
		repository.update(foodItem);
	}

	public void delete(FoodItem foodItem)
	{
		repository.delete(foodItem);
	}

	public LiveData<FoodItem> get(int id)
	{
		return repository.get(id);
	}
}
