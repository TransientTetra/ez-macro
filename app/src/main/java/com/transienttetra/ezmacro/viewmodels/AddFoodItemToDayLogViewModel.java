package com.transienttetra.ezmacro.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.transienttetra.ezmacro.repositories.DayLogRepository;
import com.transienttetra.ezmacro.repositories.FoodItemRepository;
import com.transienttetra.ezmacro.entities.DayLog;
import com.transienttetra.ezmacro.entities.FoodItem;
import com.transienttetra.ezmacro.relations.DayLogFoodItemCrossRef;

import java.util.List;

public class AddFoodItemToDayLogViewModel extends AndroidViewModel
{
	FoodItemRepository foodItemRepository;
	DayLogRepository dayLogRepository;

	public AddFoodItemToDayLogViewModel(@NonNull Application application)
	{
		super(application);
		foodItemRepository = new FoodItemRepository(application);
		dayLogRepository = new DayLogRepository(application);
	}

	public LiveData<List<FoodItem>> getAllFoodItems()
	{
		return foodItemRepository.getAll();
	}

	public void attach(DayLog dayLog, FoodItem foodItem)
	{
		dayLogRepository.insert(new DayLogFoodItemCrossRef(dayLog.getDayLogDate(), foodItem.getFoodItemId()));
	}

	public void attach(DayLog dayLog, FoodItem foodItem, float weight)
	{
		DayLogFoodItemCrossRef temp = new DayLogFoodItemCrossRef(dayLog.getDayLogDate(), foodItem.getFoodItemId());
		temp.setWeight(weight);
		dayLogRepository.insert(temp);
	}
}
