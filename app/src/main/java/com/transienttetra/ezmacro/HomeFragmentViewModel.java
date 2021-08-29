package com.transienttetra.ezmacro;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.transienttetra.ezmacro.entities.DayLog;
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

	public void update(DayLog dayLog)
	{
		repository.update(dayLog);
	}

	public LiveData<DayLogWithFoodItems> getDayLog(LocalDate date)
	{
//		if (repository.get(date) == null)
		{
			repository.insert(new DayLog(date));
		}
		return repository.get(date);
	}
}
