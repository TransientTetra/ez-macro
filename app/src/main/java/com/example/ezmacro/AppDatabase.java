package com.example.ezmacro;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {FoodItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
	private static AppDatabase instance;

	public abstract FoodItemDao FoodItemDao();

	public static synchronized AppDatabase getInstance(Context context)
	{
		if (instance == null)
		{
			instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "FoodDatabase")
				.fallbackToDestructiveMigration()
				.addCallback(roomCallback)
				.build();
		}
		return instance;
	}

	private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
	{
		@Override
		public void onCreate(@NonNull SupportSQLiteDatabase db)
		{
			super.onCreate(db);
			new PopulateDbAsyncTask(instance).execute();
		}
	};

	// todo example, remove
	private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
	{
		private FoodItemDao foodItemDao;

		public PopulateDbAsyncTask(AppDatabase db)
		{
			this.foodItemDao = db.FoodItemDao();
		}

		@Override
		protected Void doInBackground(Void... voids)
		{
			foodItemDao.insert(new FoodItem("Pizza", "", new Nutrition(), "", 0, 0, false));
			foodItemDao.insert(new FoodItem("Pizza", "", new Nutrition(), "", 0, 0, false));
			foodItemDao.insert(new FoodItem("Pizza", "", new Nutrition(), "", 0, 0, false));
			return null;
		}
	}
}
