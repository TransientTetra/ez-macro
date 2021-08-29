package com.transienttetra.ezmacro.relations;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.time.LocalDate;

@Entity(primaryKeys = {"dayLogDate", "foodItemId"})
public class DayLogFoodItemCrossRef
{
	@NonNull
	private LocalDate dayLogDate;

	private int foodItemId;

	public LocalDate getDayLogDate()
	{
		return dayLogDate;
	}

	public void setDayLogDate(LocalDate dayLogDate)
	{
		this.dayLogDate = dayLogDate;
	}

	public int getFoodItemId()
	{
		return foodItemId;
	}

	public void setFoodItemId(int foodItemId)
	{
		this.foodItemId = foodItemId;
	}
}
