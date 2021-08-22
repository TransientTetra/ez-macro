package com.transienttetra.ezmacro;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel
{
	private FoodItemRepository repository;
	private LiveData<List<FoodItem>> allFoodItems;
	public MainActivityViewModel(@NonNull Application application)
	{
		super(application);
		repository = new FoodItemRepository(application);
		allFoodItems = repository.getAll();
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

	public void deleteAll()
	{
		repository.deleteAll();
	}

	public LiveData<List<FoodItem>> getAll()
	{
		return allFoodItems;
	}
}
