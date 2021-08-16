package com.example.ezmacro;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Class representing nutrition of a product
 *
 * @author TransientTetra
 * @version 1.0
 * @since 2021-08-03
 */
public class Nutrition
{
	/**
	 * Energy in joules per 100g of the product
	 */
	private float energy;

	/**
	 * Amount of protein in grams in 100g of the product
	 */
	private float protein;

	/**
	 * Amount of all fats in grams in 100g of the product
	 */
	private float fats;

	/**
	 * Amount of all carbohydrates in grams in 100g of the product
	 */
	private float carbohydrates; // overall carbohydrates in grams

	@Ignore
	public Nutrition()
	{
		this.energy = 0;
		this.protein = 0;
		this.fats = 0;
		this.carbohydrates = 0;
	}

	public Nutrition(float energy, float protein, float fats, float carbohydrates)
	{
		this.energy = energy;
		this.protein = protein;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
	}

	public float getEnergy()
	{
		return energy;
	}

	public void setEnergy(float energy)
	{
		this.energy = energy;
	}

	public float getProtein()
	{
		return protein;
	}

	public void setProtein(float protein)
	{
		this.protein = protein;
	}

	public float getFats()
	{
		return fats;
	}

	public void setFats(float fats)
	{
		this.fats = fats;
	}

	public float getCarbohydrates()
	{
		return carbohydrates;
	}

	public void setCarbohydrates(float carbohydrates)
	{
		this.carbohydrates = carbohydrates;
	}
}
