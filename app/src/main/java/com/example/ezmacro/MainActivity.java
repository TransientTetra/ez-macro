package com.example.ezmacro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
	private ProgressBar caloriesProgressBar;
	private TextView caloriesPercent;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		caloriesProgressBar = findViewById(R.id.caloriesProgressBar);
		caloriesPercent = findViewById(R.id.caloriesProgressPercent);

		setCalorieProgress(39);
	}

	public void setCalorieProgress(int percent)
	{
		// clamping between 0 and 100
		percent = Math.min(Math.max(percent, 0), 100);
		caloriesProgressBar.setProgress(percent);
		caloriesPercent.setText(caloriesProgressBar.getProgress() + "%");
	}
}