package com.transienttetra.ezmacro.relations;


import androidx.room.Embedded;

import com.transienttetra.ezmacro.entities.FoodItem;
import com.transienttetra.ezmacro.entities.Nutrition;

public class LoggedFoodItem
{
	private float loggedWeight;
	@Embedded
	private FoodItem foodItem;

	public LoggedFoodItem(float loggedWeight, FoodItem foodItem)
	{
		this.loggedWeight = loggedWeight;
		this.foodItem = foodItem;
	}

	public FoodItem getFoodItem()
	{
		return foodItem;
	}

	public void setFoodItem(FoodItem foodItem)
	{
		this.foodItem = foodItem;
	}

	public float getLoggedWeight()
	{
		return loggedWeight;
	}

	public void setLoggedWeight(float loggedWeight)
	{
		this.loggedWeight = loggedWeight;
	}

	public Nutrition getNutrition()
	{
		return foodItem.getNutrition().multiply(loggedWeight / 100);
	}
}
