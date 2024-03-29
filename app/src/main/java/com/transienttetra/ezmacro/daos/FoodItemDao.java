package com.transienttetra.ezmacro.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.transienttetra.ezmacro.entities.FoodItem;

import java.util.List;

/**
 * Food item data access object
 *
 * @author TransientTetra
 * @version 1.0
 * @since 2021-08-12
 */

@Dao
public interface FoodItemDao
{
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(FoodItem foodItem);

	@Update
	void update(FoodItem foodItem);

	@Delete
	void delete(FoodItem foodItem);

	@Query("DELETE FROM FoodItem")
	void deleteAll();

	@Query("SELECT * FROM FoodItem ORDER BY foodItemId")
	LiveData<List<FoodItem>> getAll();

	@Query("SELECT * FROM FoodItem WHERE foodItemId = :id")
	LiveData<FoodItem> get(int id);
}
