package com.example.ezmacro;

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
