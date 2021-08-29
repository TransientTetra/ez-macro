package com.transienttetra.ezmacro.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezmacro.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.transienttetra.ezmacro.FoodItemAdapter;
import com.transienttetra.ezmacro.HomeFragmentViewModel;
import com.transienttetra.ezmacro.activities.AddEditFoodItemActivity;
import com.transienttetra.ezmacro.entities.FoodItem;
import com.transienttetra.ezmacro.entities.Nutrition;
import com.transienttetra.ezmacro.relations.DayLogWithFoodItems;
import com.transienttetra.ezmacro.util.EnergyConverter;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.Date;

public class HomeFragment extends Fragment
{
	private ProgressBar energyProgressBar;
	private TextView energyPercent;

	private ProgressBar proteinProgressBar;
	private TextView proteinPercent;

	private ProgressBar fatProgressBar;
	private TextView fatPercent;

	private ProgressBar carbProgressBar;
	private TextView carbPercent;

	private Button chooseDateButton;
	private DatePickerDialog datePickerDialog;

	private HomeFragmentViewModel homeFragmentViewModel;
	private Nutrition goal;
	private FoodItemAdapter foodItemAdapter;

	private LocalDate currentDate;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.home_fragment, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		energyProgressBar = getView().findViewById(R.id.energyProgressBar);
		energyPercent = getView().findViewById(R.id.energyProgressPercent);
		proteinProgressBar = getView().findViewById(R.id.proteinProgressBar);
		proteinPercent = getView().findViewById(R.id.proteinProgressPercent);
		fatProgressBar = getView().findViewById(R.id.fatProgressBar);
		fatPercent = getView().findViewById(R.id.fatProgressPercent);
		carbProgressBar = getView().findViewById(R.id.carbProgressBar);
		carbPercent = getView().findViewById(R.id.carbProgressPercent);
		chooseDateButton = getView().findViewById(R.id.chooseDateButton);

		currentDate = LocalDate.now();

		DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
		{
			@Override
			public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
			{
				currentDate = LocalDate.of(year, month, dayOfMonth);
				updateDayLog();
			}
		};

		datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener, currentDate.getYear(),
			currentDate.getMonthValue(), currentDate.getDayOfMonth());

		chooseDateButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				datePickerDialog.show();
			}
		});

		// todo temporary
		goal = new Nutrition(EnergyConverter.kcalToJoule(3000), 200, 90, 250);

		FloatingActionButton addFoodItemButton = getView().findViewById(R.id.addFoodItemButton);
		addFoodItemButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
//				Intent intent = new Intent(getActivity(), AddEditFoodItemActivity.class);
//				startActivity(intent);
			}
		});

		RecyclerView foodItemsView = getView().findViewById(R.id.foodItemsView);
		foodItemAdapter = new FoodItemAdapter();
		foodItemsView.setAdapter(foodItemAdapter);
		foodItemsView.setLayoutManager(new LinearLayoutManager(getActivity()));
		foodItemsView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

		homeFragmentViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(HomeFragmentViewModel.class);
		updateDayLog();

		new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
		{
			@Override
			public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
			{
				return false;
			}

			@Override
			public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
			{
				FoodItem toDelete = foodItemAdapter.getFoodItemAt(viewHolder.getAdapterPosition());
				String foodName = toDelete.getName();
//				homeFragmentViewModel.delete(toDelete);
				Toast.makeText(getActivity().getApplicationContext(), getString(R.string.deleteFoodItemMainActivity) + foodName, Toast.LENGTH_SHORT).show();
			}
		}).attachToRecyclerView(foodItemsView);

		foodItemAdapter.setOnItemClickListener(new FoodItemAdapter.OnItemClickListener()
		{
			@Override
			public void onItemClick(FoodItem foodItem)
			{
				Intent intent = new Intent(getActivity(), AddEditFoodItemActivity.class);
				intent.putExtra(AddEditFoodItemActivity.EXTRA_ID, foodItem.getFoodItemId());
				startActivity(intent);
			}
		});
	}

	private void updateDayLog()
	{
		chooseDateButton.setText(currentDate.toString());
		homeFragmentViewModel.getDayLog(currentDate).observe(this, new Observer<DayLogWithFoodItems>()
		{
			@Override
			public void onChanged(DayLogWithFoodItems dayLog)
			{
				Nutrition progress = new Nutrition();
				for (FoodItem foodItem : dayLog.getFoodItemList())
				{
					progress.add(foodItem.getNutrition());
				}
				setProgress(goal, progress);
				foodItemAdapter.submitList(dayLog.getFoodItemList());
			}
		});
	}

	private void setProgress(Nutrition target, Nutrition current)
	{
		setEnergyProgress(Math.round(current.getEnergy() / target.getEnergy() * 100));
		setProteinProgress(Math.round(current.getProtein() / target.getProtein() * 100));
		setFatProgress(Math.round(current.getFats() / target.getFats() * 100));
		setCarbProgress(Math.round(current.getCarbohydrates() / target.getCarbohydrates() * 100));
	}

	private void setSingleProgress(int percent, ProgressBar progressBar, TextView textView)
	{
		percent = Math.max(percent, 0);
		// we are using secondary progress for primary progress, and using progress property as overconsumption
		// progress
		if (percent <= 100)
		{
			progressBar.setSecondaryProgress(percent);
			progressBar.setProgress(0);
		}
		else
		{
			progressBar.setSecondaryProgress(100);
			progressBar.setProgress(percent - 100);
		}
		textView.setText(percent + "%");
	}

	public void setEnergyProgress(int percent)
	{
		setSingleProgress(percent, energyProgressBar, energyPercent);
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
