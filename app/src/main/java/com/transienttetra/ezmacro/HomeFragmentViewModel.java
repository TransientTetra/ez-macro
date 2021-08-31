package com.transienttetra.ezmacro;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.transienttetra.ezmacro.entities.DayLog;
import com.transienttetra.ezmacro.entities.FoodItem;
import com.transienttetra.ezmacro.relations.DayLogFoodItemCrossRef;
import com.transienttetra.ezmacro.relations.DayLogWithFoodItems;

import java.time.LocalDate;

public class HomeFragmentViewModel extends AndroidViewModel
{
	private DayLogRepository repository;
	public HomeFragmentViewModel(@NonNull Application application)
	{
		super(application);
		repository = new DayLogRepository(application);
	}

	public void insert(DayLog dayLog)
	{
		repository.insert(dayLog);
	}

	public void detach(DayLog daylog, FoodItem foodItem)
	{
		repository.delete(new DayLogFoodItemCrossRef(daylog.getDayLogDate(), foodItem.getFoodItemId()));
	}

	public LiveData<DayLogWithFoodItems> getDayLog(LocalDate date)
	{
		// don't like this
		repository.insert(new DayLog(date));
		return repository.get(date);
	}
}
