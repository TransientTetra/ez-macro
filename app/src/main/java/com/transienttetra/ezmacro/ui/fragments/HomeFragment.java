package com.transienttetra.ezmacro.ui.fragments;

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
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.transienttetra.ezmacro.relations.LoggedFoodItem;
import com.transienttetra.ezmacro.ui.adapters.LoggedFoodItemAdapter;
import com.transienttetra.ezmacro.viewmodels.HomeFragmentViewModel;
import com.transienttetra.ezmacro.R;
import com.transienttetra.ezmacro.ui.activities.AddFoodItemToDayLogActivity;
import com.transienttetra.ezmacro.entities.FoodItem;
import com.transienttetra.ezmacro.entities.Nutrition;

import java.time.LocalDate;
import java.util.List;

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

	private HomeFragmentViewModel viewModel;
	private LoggedFoodItemAdapter loggedFoodItemAdapter;
	private ViewSwitcher viewSwitcher;

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
		viewSwitcher = getView().findViewById(R.id.viewSwitcher);

		viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(HomeFragmentViewModel.class);
		viewModel.setListener(new HomeFragmentViewModel.OnDateChangedListener()
		{
			@Override
			public void onDateChanged(List<LoggedFoodItem> loggedFoodItemList)
			{
				if (loggedFoodItemList.size() > 0)
				{
					if (viewSwitcher.getNextView().getId() == R.id.foodItemsView)
						viewSwitcher.showNext();
				}
				else if (viewSwitcher.getNextView().getId() == R.id.emptyText)
					viewSwitcher.showNext();

				setProgress(viewModel.getGoal(), viewModel.getProgress());
				loggedFoodItemAdapter.submitList(loggedFoodItemList);
			}
		});

		DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
		{
			@Override
			public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
			{
				chooseDateButton.setText(LocalDate.of(year, month, dayOfMonth).toString());
				viewModel.notifyDateChanged(LocalDate.of(year, month, dayOfMonth), HomeFragment.this);
			}
		};
		datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener, viewModel.getCurrentDate().getYear(),
			viewModel.getCurrentDate().getMonthValue(), viewModel.getCurrentDate().getDayOfMonth());

		chooseDateButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				datePickerDialog.show();
			}
		});

		FloatingActionButton addFoodItemButton = getView().findViewById(R.id.addFoodItemButton);
		addFoodItemButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getActivity(), AddFoodItemToDayLogActivity.class);
				intent.putExtra(AddFoodItemToDayLogActivity.EXTRA_DAY_LOG_ID, viewModel.getCurrentDate());
				startActivity(intent);
			}
		});

		RecyclerView foodItemsView = getView().findViewById(R.id.foodItemsView);
		loggedFoodItemAdapter = new LoggedFoodItemAdapter();
		foodItemsView.setAdapter(loggedFoodItemAdapter);
		foodItemsView.setLayoutManager(new LinearLayoutManager(getActivity()));
		foodItemsView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

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
				FoodItem toDelete = loggedFoodItemAdapter.getLoggedFoodItemAt(viewHolder.getAdapterPosition()).getFoodItem();
				String foodName = toDelete.getName();
				viewModel.detach(toDelete);
				Toast.makeText(getActivity().getApplicationContext(), getString(R.string.food_item_detached) + foodName, Toast.LENGTH_SHORT).show();
			}
		}).attachToRecyclerView(foodItemsView);

		loggedFoodItemAdapter.setOnItemClickListener(new LoggedFoodItemAdapter.OnItemClickListener()
		{
			@Override
			public void onItemClick(LoggedFoodItem loggedFoodItem)
			{
//				Intent intent = new Intent(getActivity(), AddEditFoodItemActivity.class);
//				intent.putExtra(AddEditFoodItemActivity.EXTRA_ID, foodItem.getFoodItemId());
//				startActivity(intent);
			}
		});

		chooseDateButton.setText(viewModel.getCurrentDate().toString());
		viewModel.notifyDateChanged(viewModel.getCurrentDate(), HomeFragment.this);
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
