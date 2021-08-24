package com.transienttetra.ezmacro;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
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
	private float servings;

	/**
	 * How much the item weighs overall
	 */
	private float weight;

	/**
	 * Is the item favorited or not
	 */
	private boolean isFavorite;

	@Ignore
	public FoodItem()
	{
		this.name = "";
		this.description = "";
		this.nutrition = new Nutrition();
		this.barcode = "";
		this.servings = 0;
		this.weight = 0;
		this.isFavorite = false;
	}

	public FoodItem(String name, String description, Nutrition nutrition, String barcode, float servings, float weight, boolean isFavorite)
	{
		this.name = name;
		this.description = description;
		this.nutrition = nutrition;
		this.barcode = barcode;
		this.servings = servings;
		this.weight = weight;
		this.isFavorite = isFavorite;
	}

	public boolean equals(FoodItem other)
	{
		boolean ret = true;
		ret &= this.getBarcode().equals(other.getBarcode());
		ret &= this.isFavorite() == other.isFavorite();
		ret &= this.getName().equals(other.getName());
		ret &= this.getDescription().equals(other.getDescription());
		ret &= this.getNutrition().equals(other.getNutrition());
		ret &= this.getServings() == other.getServings();
		ret &= this.getWeight() == other.getWeight();
		return ret;
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

	public float getServings()
	{
		return servings;
	}

	public void setServings(float servings)
	{
		this.servings = servings;
	}

	public float getWeight()
	{
		return weight;
	}

	public void setWeight(float weight)
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
