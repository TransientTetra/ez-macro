package com.transienttetra.ezmacro.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.transienttetra.ezmacro.entities.DayLog;
import com.transienttetra.ezmacro.relations.LoggedFoodItem;
import com.transienttetra.ezmacro.relations.DayLogFoodItemCrossRef;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface DayLogDao
{
	@Transaction
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	void insert(DayLog dayLog);

	@Transaction
	@Update
	void update(DayLog dayLog);

	@Transaction
	@Delete
	void delete(DayLog dayLog);

	@Transaction
	@Query("DELETE FROM DayLog")
	void deleteAll();

	@Transaction
	@Query("SELECT DayLogFoodItemCrossRef.id, DayLogFoodItemCrossRef.loggedWeight, FoodItem.* FROM DayLogFoodItemCrossRef INNER JOIN FoodItem ON DayLogFoodItemCrossRef.foodItemId = FoodItem.foodItemId WHERE DayLogFoodItemCrossRef.dayLogDate = :dayLogDate")
	LiveData<List<LoggedFoodItem>> getLoggedFoodItems(LocalDate dayLogDate);

	@Transaction
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(DayLogFoodItemCrossRef dayLogFoodItemCrossRef);

	@Transaction
	@Delete
	void delete(DayLogFoodItemCrossRef dayLogFoodItemCrossRef);
}
