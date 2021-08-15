package com.example.ezmacro;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FoodItemRepository
{
	private FoodItemDao foodItemDao;
	private LiveData<List<FoodItem>> allFoodItems;

	public FoodItemRepository(Application application)
	{
		AppDatabase database = AppDatabase.getInstance(application);
		foodItemDao = database.FoodItemDao();
		allFoodItems = foodItemDao.getAll();
	}

	public void insert(FoodItem foodItem)
	{
		new InsertAsyncTask(foodItemDao).execute(foodItem);
	}

	public void update(FoodItem foodItem)
	{
		new UpdateAsyncTask(foodItemDao).execute(foodItem);
	}

	public void delete(FoodItem foodItem)
	{
		new DeleteAsyncTask(foodItemDao).execute(foodItem);
	}

	public void deleteAll()
	{
		new DeleteAllAsyncTask(foodItemDao).execute();
	}

	public LiveData<List<FoodItem>> getAll()
	{
		return allFoodItems;
	}

	private static class InsertAsyncTask extends AsyncTask<FoodItem, Void, Void>
	{
		private final FoodItemDao foodItemDao;

		private InsertAsyncTask(FoodItemDao foodItemDao)
		{
			this.foodItemDao = foodItemDao;
		}
		@Override
		protected Void doInBackground(FoodItem... foodItems)
		{
			foodItemDao.insert(foodItems[0]);
			return null;
		}
	}

	private static class UpdateAsyncTask extends AsyncTask<FoodItem, Void, Void>
	{
		private final FoodItemDao foodItemDao;

		private UpdateAsyncTask(FoodItemDao foodItemDao)
		{
			this.foodItemDao = foodItemDao;
		}
		@Override
		protected Void doInBackground(FoodItem... foodItems)
		{
			foodItemDao.update(foodItems[0]);
			return null;
		}
	}

	private static class DeleteAsyncTask extends AsyncTask<FoodItem, Void, Void>
	{
		private final FoodItemDao foodItemDao;

		private DeleteAsyncTask(FoodItemDao foodItemDao)
		{
			this.foodItemDao = foodItemDao;
		}
		@Override
		protected Void doInBackground(FoodItem... foodItems)
		{
			foodItemDao.delete(foodItems[0]);
			return null;
		}
	}

	private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void>
	{
		private final FoodItemDao foodItemDao;

		private DeleteAllAsyncTask(FoodItemDao foodItemDao)
		{
			this.foodItemDao = foodItemDao;
		}
		@Override
		protected Void doInBackground(Void... voids)
		{
			foodItemDao.deleteAll();
			return null;
		}
	}
}
