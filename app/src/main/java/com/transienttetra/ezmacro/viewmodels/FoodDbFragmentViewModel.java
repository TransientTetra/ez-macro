package com.transienttetra.ezmacro.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.transienttetra.ezmacro.repositories.FoodItemRepository;
import com.transienttetra.ezmacro.entities.FoodItem;

import java.util.List;

public class FoodDbFragmentViewModel extends AndroidViewModel
{
	private FoodItemRepository repository;
	private LiveData<List<FoodItem>> allFoodItems;
	public FoodDbFragmentViewModel(@NonNull Application application)
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
