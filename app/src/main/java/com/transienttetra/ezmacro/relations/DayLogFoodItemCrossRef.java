package com.transienttetra.ezmacro.relations;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.transienttetra.ezmacro.entities.DayLog;
import com.transienttetra.ezmacro.entities.FoodItem;

import java.time.LocalDate;

@Entity(indices = {@Index("foodItemId"), @Index("dayLogDate")},
	foreignKeys = {
		@ForeignKey(onDelete = CASCADE,
			entity = DayLog.class,
			parentColumns = "dayLogDate",
			childColumns = "dayLogDate"),
		@ForeignKey(onDelete = CASCADE,
			entity = FoodItem.class,
			parentColumns = "foodItemId",
			childColumns = "foodItemId")})
public class DayLogFoodItemCrossRef
{
	@PrimaryKey(autoGenerate = true)
	private int id;

	@NonNull
	private LocalDate dayLogDate;

	private int foodItemId;

	private float loggedWeight;

	public DayLogFoodItemCrossRef(@NonNull LocalDate dayLogDate, int foodItemId)
	{
		this.dayLogDate = dayLogDate;
		this.foodItemId = foodItemId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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

	public float getLoggedWeight()
	{
		return loggedWeight;
	}

	public void setLoggedWeight(float loggedWeight)
	{
		this.loggedWeight = loggedWeight;
	}
}
