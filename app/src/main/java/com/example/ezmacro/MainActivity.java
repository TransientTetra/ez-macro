package com.example.ezmacro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
	private ProgressBar caloriesProgressBar;
	private TextView caloriesPercent;
	
	private ProgressBar proteinProgressBar;
	private TextView proteinPercent;

	private ProgressBar fatProgressBar;
	private TextView fatPercent;

	private ProgressBar carbProgressBar;
	private TextView carbPercent;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		caloriesProgressBar = findViewById(R.id.caloriesProgressBar);
		caloriesPercent = findViewById(R.id.caloriesProgressPercent);
		proteinProgressBar = findViewById(R.id.proteinProgressBar);
		proteinPercent = findViewById(R.id.proteinProgressPercent);
		fatProgressBar = findViewById(R.id.fatProgressBar);
		fatPercent = findViewById(R.id.fatProgressPercent);
		carbProgressBar = findViewById(R.id.carbProgressBar);
		carbPercent = findViewById(R.id.carbProgressPercent);

		setCalorieProgress(39);
		setProteinProgress(50);
		setFatProgress(80);
		setCarbProgress(120);
	}

	private void setSingleProgress(int percent, ProgressBar progressBar, TextView textView)
	{
		// todo what when over 100%?
		percent = Math.max(percent, 0);
		progressBar.setProgress(percent);
		textView.setText(progressBar.getProgress() + "%");
	}

	public void setCalorieProgress(int percent)
	{
		setSingleProgress(percent, caloriesProgressBar, caloriesPercent);
	}

	public void setProteinProgress(int percent)
	{
		setSingleProgress(percent, proteinProgressBar, proteinPercent);
	}

	public void setFatProgress(int percent)
	{
		setSingleProgress(percent, fatProgressBar, fatPercent);
	}

	public void setCarbProgress(int percent)
	{
		setSingleProgress(percent, carbProgressBar, carbPercent);
	}
}