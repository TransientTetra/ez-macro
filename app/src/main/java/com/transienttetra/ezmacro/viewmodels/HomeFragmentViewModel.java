package com.transienttetra.ezmacro.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.transienttetra.ezmacro.entities.Nutrition;
import com.transienttetra.ezmacro.repositories.DayLogRepository;
import com.transienttetra.ezmacro.entities.DayLog;
import com.transienttetra.ezmacro.entities.FoodItem;
import com.transienttetra.ezmacro.relations.DayLogFoodItemCrossRef;
import com.transienttetra.ezmacro.relations.DayLogWithFoodItems;
import com.transienttetra.ezmacro.util.EnergyConverter;

import java.time.LocalDate;

public class HomeFragmentViewModel extends AndroidViewModel
{
	private DayLogRepository repository;
	private Nutrition goal;
	private DayLog current;

	public HomeFragmentViewModel(@NonNull Application application)
	{
		super(application);
		repository = new DayLogRepository(application);
		current = new DayLog(LocalDate.now());

		// todo temporary
		goal = new Nutrition(EnergyConverter.kcalToJoule(3000), 200, 90, 250);
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

	public Nutrition getGoal()
	{
		return goal;
	}

	public void setGoal(Nutrition goal)
	{
		this.goal = goal;
	}

	public DayLog getCurrentDayLog()
	{
		return current;
	}

	public void setCurrentDayLog(DayLog current)
	{
		this.current = current;
	}
}
