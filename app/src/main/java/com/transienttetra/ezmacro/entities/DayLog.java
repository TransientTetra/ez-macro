package com.transienttetra.ezmacro.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity
public class DayLog
{
	@PrimaryKey
	@NonNull
	private LocalDate dayLogDate;

	public DayLog(LocalDate dayLogDate)
	{
		this.dayLogDate = dayLogDate;
	}

	public LocalDate getDayLogDate()
	{
		return dayLogDate;
	}

	public void setDayLogDate(LocalDate date)
	{
		this.dayLogDate = date;
	}
}
