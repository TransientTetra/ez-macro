package com.example.ezmacro;

import androidx.annotation.Nullable;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The class representing a single food item, with a given name
 *
 * @author TransientTetra
 * @version 1.0
 * @since 2021-08-03
 */

@Entity
public class FoodItem
{
	/**
	 * Unique ID
	 */
	@PrimaryKey(autoGenerate = true)
	private int id;

	/**
	 * The name of the food item
	 */
	private String name;

	/**
	 * Description of the food item
	 */
	private String description;

	/**
	 * The {@link Nutrition} value of the food item
	 */
	@Embedded
	private Nutrition nutrition;

	/**
	 * An optional barcode of the item
	 */
	
	private String barcode;

	/**
	 * How many servings the item contains
	 */
	private int servings;

	/**
	 * How much the item weighs overall
	 */
	private int weight;

	/**
	 * Is the item favorited or not
	 */
	private boolean isFavorite;

	public FoodItem()
	{
	}

	public FoodItem(String name, String description, Nutrition nutrition, String barcode, int servings, int weight, boolean isFavorite)
	{
		this.name = name;
		this.description = description;
		this.nutrition = nutrition;
		this.barcode = barcode;
		this.servings = servings;
		this.weight = weight;
		this.isFavorite = isFavorite;
	}

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

	public String getBarcode()
	{
		return barcode;
	}

	public void setBarcode( String barcode)
	{
		this.barcode = barcode;
	}

	public int getServings()
	{
		return servings;
	}

	public void setServings(int servings)
	{
		this.servings = servings;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public boolean isFavorite()
	{
		return isFavorite;
	}

	public void setFavorite(boolean favorite)
	{
		isFavorite = favorite;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
