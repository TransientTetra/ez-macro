package com.transienttetra.ezmacro;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.transienttetra.ezmacro.daos.DayLogDao;
import com.transienttetra.ezmacro.daos.FoodItemDao;
import com.transienttetra.ezmacro.entities.DayLog;
import com.transienttetra.ezmacro.entities.FoodItem;
import com.transienttetra.ezmacro.relations.DayLogFoodItemCrossRef;
import com.transienttetra.ezmacro.util.DateConverter;

@Database(entities = {FoodItem.class, DayLog.class, DayLogFoodItemCrossRef.class}, version = 8)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase
{
	private static AppDatabase instance;

	public abstract FoodItemDao FoodItemDao();
	public abstract DayLogDao DayLogDao();

	public static synchronized AppDatabase getInstance(Context context)
	{
		if (instance == null)
		{
			instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase")
				.fallbackToDestructiveMigration()
				.build();
		}
		return instance;
	}
}
