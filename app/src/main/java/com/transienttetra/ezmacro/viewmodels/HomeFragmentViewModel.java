package com.transienttetra.ezmacro.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.transienttetra.ezmacro.relations.LoggedFoodItem;
import com.transienttetra.ezmacro.entities.Nutrition;
import com.transienttetra.ezmacro.repositories.DayLogRepository;
import com.transienttetra.ezmacro.entities.DayLog;
import com.transienttetra.ezmacro.entities.FoodItem;
import com.transienttetra.ezmacro.relations.DayLogFoodItemCrossRef;
import com.transienttetra.ezmacro.util.EnergyConverter;

import java.time.LocalDate;
import java.util.List;

public class HomeFragmentViewModel extends AndroidViewModel
{
	private DayLogRepository repository;
	private Nutrition goal;
	private Nutrition progress;
	private OnDateChangedListener listener;
	private LocalDate currentDate;

	public interface OnDateChangedListener
	{
		void onDateChanged(List<LoggedFoodItem> foodItemList);
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
		repository.insert(new DayLog(date));
		repository.getLoggedFoodItems(date).observe(lifecycleOwner, new Observer<List<LoggedFoodItem>>()
		{
			@Override
			public void onChanged(List<LoggedFoodItem> loggedFoodItems)
			{
				updateProgress(loggedFoodItems);
				listener.onDateChanged(loggedFoodItems);
			}
		});
	}

	private void updateProgress(List<LoggedFoodItem> loggedFoodItems)
	{
		progress = new Nutrition();
		for (LoggedFoodItem foodItem : loggedFoodItems)
		{
			progress.add(foodItem.getNutrition());
		}
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

	public Nutrition getProgress()
	{
		return progress;
	}

	public void setProgress(Nutrition progress)
	{
		this.progress = progress;
	}

	public LocalDate getCurrentDate()
	{
		return currentDate;
	}
}
