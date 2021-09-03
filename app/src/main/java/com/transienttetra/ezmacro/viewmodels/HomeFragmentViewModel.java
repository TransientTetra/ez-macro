package com.transienttetra.ezmacro.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

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
	private OnDateChangedListener listener;
	private LocalDate currentDate;

	public interface OnDateChangedListener
	{
		void onDateChanged(DayLogWithFoodItems dayLog);
	}

	public HomeFragmentViewModel(@NonNull Application application)
	{
		super(application);
		repository = new DayLogRepository(application);
		currentDate = LocalDate.now();

		// todo temporary
		goal = new Nutrition(EnergyConverter.kcalToJoule(3000), 200, 90, 250);
	}

	public void notifyDateChanged(LocalDate date, LifecycleOwner lifecycleOwner)
	{
		currentDate = date;
		repository.get(date).observe(lifecycleOwner, new Observer<DayLogWithFoodItems>()
		{
			@Override
			public void onChanged(DayLogWithFoodItems dayLogWithFoodItems)
			{
				listener.onDateChanged(dayLogWithFoodItems);
			}
		});
	}

	public void setListener(OnDateChangedListener listener)
	{
		this.listener = listener;
	}

	public void insert(DayLog dayLog)
	{
		repository.insert(dayLog);
	}

	public void detach(DayLog daylog, FoodItem foodItem)
	{
		repository.delete(new DayLogFoodItemCrossRef(daylog.getDayLogDate(), foodItem.getFoodItemId()));
	}

	public void detach(FoodItem foodItem)
	{
		detach(new DayLog(currentDate), foodItem);
	}

	public Nutrition getGoal()
	{
		return goal;
	}

	public void setGoal(Nutrition goal)
	{
		this.goal = goal;
	}

	public LocalDate getCurrentDate()
	{
		return currentDate;
	}
}
