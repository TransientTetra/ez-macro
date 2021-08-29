package com.transienttetra.ezmacro.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.transienttetra.ezmacro.entities.DayLog;
import com.transienttetra.ezmacro.entities.FoodItem;

import java.util.List;

public class DayLogWithFoodItems
{
	@Embedded
	private DayLog dayLog;

	@Relation(
		parentColumn = "dayLogDate",
		entityColumn = "foodItemId",
		associateBy = @Junction(DayLogFoodItemCrossRef.class)
	)
	private List<FoodItem> foodItemList;

	public DayLog getDayLog()
	{
		return dayLog;
	}

	public void setDayLog(DayLog dayLog)
	{
		this.dayLog = dayLog;
	}

	public List<FoodItem> getFoodItemList()
	{
		return foodItemList;
	}

	public void setFoodItemList(List<FoodItem> foodItemList)
	{
		this.foodItemList = foodItemList;
	}
}
