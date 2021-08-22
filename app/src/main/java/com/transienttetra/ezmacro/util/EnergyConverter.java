package com.transienttetra.ezmacro.util;

/**
 * Utility class for energy unit conversion methods
 *
 * @author TransientTetra
 * @version 1.0
 * @since 2021-08-03
 */
public class EnergyConverter
{
	/**
	 * Converts energy given in joules to kilojoules
	 * @param joule energy in joules
	 * @return energy in kilojoules
	 */
	public static float jouleToKilojoule(float joule)
	{
		return joule / 1000f;
	}

	/**
	 * Converts energy given in kilojoules to joules
	 * @param kilojoule energy in kilojoules
	 * @return energy in joules
	 */
	public static float kilojouleToJoule(float kilojoule)
	{
		return kilojoule * 1000f;
	}

	/**
	 * Converts energy given in joules to food kcal
	 * @param joule energy in joules
	 * @return energy in kcal
	 */
	public static float jouleToKcal(float joule)
	{
		return joule / 4184f;
	}


	/**
	 * Converts energy given in food kcal to joules
	 * @param kcal energy in joules
	 * @return energy in joules
	 */
	public static float kcalToJoule(float kcal)
	{
		return kcal * 4184f;
	}
}
