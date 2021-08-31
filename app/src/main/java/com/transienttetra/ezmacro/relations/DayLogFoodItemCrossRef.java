package com.transienttetra.ezmacro.relations;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;

import java.time.LocalDate;

@Entity(primaryKeys = {"dayLogDate", "foodItemId"}, indices = {@Index("foodItemId")})
public class DayLogFoodItemCrossRef
{
	@NonNull
	private LocalDate dayLogDate;

	private int foodItemId;

	private float weight;

	public DayLogFoodItemCrossRef(@NonNull LocalDate dayLogDate, int foodItemId)
	{
		this.dayLogDate = dayLogDate;
		this.foodItemId = foodItemId;
	}

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

	public float getWeight()
	{
		return weight;
	}

	public void setWeight(float weight)
	{
		this.weight = weight;
	}
}
