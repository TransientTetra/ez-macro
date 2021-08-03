package com.example.ezmacro;

import androidx.annotation.Nullable;

/**
 * The class representing a single food item, with a given name
 *
 * @author TransientTetra
 * @version 1.0
 * @since 2021-08-03
 */
public class FoodItem
{
	/**
	 * The name of the food item
	 */
	private String name;

	/**
	 * The {@link Nutrition} value of the food item
	 */
	private Nutrition nutrition;

	/**
	 * An optional barcode of the item
	 */
	@Nullable
	private String barcode;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Nutrition getNutrition()
	{
		return nutrition;
	}

	public void setNutrition(Nutrition nutrition)
	{
		this.nutrition = nutrition;
	}

	@Nullable
	public String getBarcode()
	{
		return barcode;
	}

	public void setBarcode(@Nullable String barcode)
	{
		this.barcode = barcode;
	}
}
