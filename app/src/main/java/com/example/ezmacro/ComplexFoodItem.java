package com.example.ezmacro;

import java.util.List;

/**
 * The class representing a food item composed of other food items. It is treated as a food item since it inherits
 * from the food item class. Can for example represent a meal composed from ingredients, each ingredient being a food
 * item.
 *
 * @author TransientTetra
 * @version 1.0
 * @since 2021-08-03
 */
public class ComplexFoodItem extends FoodItem
{
	List<FoodItem> ingredients;
}
