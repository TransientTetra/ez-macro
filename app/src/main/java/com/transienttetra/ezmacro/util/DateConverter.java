package com.transienttetra.ezmacro.util;

import android.os.Build;

import androidx.room.TypeConverter;

import java.time.LocalDate;

public class DateConverter
{
	@TypeConverter
	public static LocalDate fromString(String val)
	{
		return val == null ? null : LocalDate.parse(val);
	}

	@TypeConverter
	public static String dateToString(LocalDate date)
	{
		return date == null ? null : date.toString();
	}
}
