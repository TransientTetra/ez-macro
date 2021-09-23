package com.transienttetra.ezmacro.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.transienttetra.ezmacro.AppDatabase;
import com.transienttetra.ezmacro.daos.DayLogDao;
import com.transienttetra.ezmacro.entities.DayLog;
import com.transienttetra.ezmacro.entities.LoggedFoodItem;
import com.transienttetra.ezmacro.relations.DayLogFoodItemCrossRef;
import com.transienttetra.ezmacro.relations.DayLogWithFoodItems;

import java.time.LocalDate;
import java.util.List;

public class DayLogRepository
{
	private DayLogDao dayLogDao;
	private LiveData<List<DayLogWithFoodItems>> allDayLogs;

	public DayLogRepository(Application application)
	{
		AppDatabase database = AppDatabase.getInstance(application);
		dayLogDao = database.DayLogDao();
		allDayLogs = dayLogDao.getAll();
	}

	public void insert(DayLog dayLog)
	{
		new InsertAsyncTask(dayLogDao).execute(dayLog);
	}

	public void update(DayLog dayLog)
	{
		new UpdateAsyncTask(dayLogDao).execute(dayLog);
	}

	public void delete(DayLog dayLog)
	{
		new DeleteAsyncTask(dayLogDao).execute(dayLog);
	}

	public void deleteAll()
	{
		new DeleteAllAsyncTask(dayLogDao).execute();
	}

	public LiveData<List<DayLogWithFoodItems>> getAll()
	{
		return allDayLogs;
	}

	public LiveData<DayLogWithFoodItems> get(LocalDate date)
	{
		// don't like this
		insert(new DayLog(date));
		return dayLogDao.get(date);
	}

	public LiveData<List<LoggedFoodItem>> getLoggedFoodItems(LocalDate date)
	{
		return dayLogDao.getLoggedFoodItems(date);
	}

	public void insert(DayLogFoodItemCrossRef dayLogFoodItemCrossRef)
	{
		new InsertCrossRefAsyncTask(dayLogDao).execute(dayLogFoodItemCrossRef);
	}

	public void delete(DayLogFoodItemCrossRef dayLogFoodItemCrossRef)
	{
		new DeleteCrossRefAsyncTask(dayLogDao).execute(dayLogFoodItemCrossRef);
	}

	private static class InsertAsyncTask extends AsyncTask<DayLog, Void, Void>
	{
		private final DayLogDao dayLogDao;

		private InsertAsyncTask(DayLogDao dayLogDao)
		{
			this.dayLogDao = dayLogDao;
		}
		@Override
		protected Void doInBackground(DayLog... dayLog)
		{
			dayLogDao.insert(dayLog[0]);
			return null;
		}
	}

	private static class UpdateAsyncTask extends AsyncTask<DayLog, Void, Void>
	{
		private final DayLogDao dayLogDao;

		private UpdateAsyncTask(DayLogDao dayLogDao)
		{
			this.dayLogDao = dayLogDao;
		}
		@Override
		protected Void doInBackground(DayLog... dayLog)
		{
			dayLogDao.update(dayLog[0]);
			return null;
		}
	}

	private static class DeleteAsyncTask extends AsyncTask<DayLog, Void, Void>
	{
		private final DayLogDao dayLogDao;

		private DeleteAsyncTask(DayLogDao dayLogDao)
		{
			this.dayLogDao = dayLogDao;
		}
		@Override
		protected Void doInBackground(DayLog... dayLog)
		{
			dayLogDao.delete(dayLog[0]);
			return null;
		}
	}

	private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void>
	{
		private final DayLogDao dayLogDao;

		private DeleteAllAsyncTask(DayLogDao dayLogDao)
		{
			this.dayLogDao = dayLogDao;
		}
		@Override
		protected Void doInBackground(Void... voids)
		{
			dayLogDao.deleteAll();
			return null;
		}
	}

	private static class InsertCrossRefAsyncTask extends AsyncTask<DayLogFoodItemCrossRef, Void, Void>
	{
		private final DayLogDao dayLogDao;

		private InsertCrossRefAsyncTask(DayLogDao dayLogDao)
		{
			this.dayLogDao = dayLogDao;
		}
		@Override
		protected Void doInBackground(DayLogFoodItemCrossRef... dayLogFoodItemCrossRefs)
		{
			dayLogDao.insert(dayLogFoodItemCrossRefs[0]);
			return null;
		}
	}

	private static class DeleteCrossRefAsyncTask extends AsyncTask<DayLogFoodItemCrossRef, Void, Void>
	{
		private final DayLogDao dayLogDao;

		private DeleteCrossRefAsyncTask(DayLogDao dayLogDao)
		{
			this.dayLogDao = dayLogDao;
		}
		@Override
		protected Void doInBackground(DayLogFoodItemCrossRef... dayLogFoodItemCrossRefs)
		{
			dayLogDao.delete(dayLogFoodItemCrossRefs[0]);
			return null;
		}
	}
}
