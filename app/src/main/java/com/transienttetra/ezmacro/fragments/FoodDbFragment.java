package com.transienttetra.ezmacro.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.transienttetra.ezmacro.FoodDbFragmentViewModel;
import com.transienttetra.ezmacro.FoodItemAdapter;
import com.transienttetra.ezmacro.activities.AddEditFoodItemActivity;
import com.transienttetra.ezmacro.entities.FoodItem;

import java.util.List;

public class FoodDbFragment extends Fragment
{
	private FoodDbFragmentViewModel viewModel;
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.food_db_fragment, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		FloatingActionButton addFoodItemButton = getView().findViewById(R.id.addFoodItemButton);
		addFoodItemButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getActivity(), AddEditFoodItemActivity.class);
				startActivity(intent);
			}
		});

		RecyclerView foodItemsView = getView().findViewById(R.id.foodItemsView);
		FoodItemAdapter foodItemAdapter = new FoodItemAdapter();
		foodItemsView.setAdapter(foodItemAdapter);
		foodItemsView.setLayoutManager(new LinearLayoutManager(getActivity()));
		foodItemsView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
		viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(FoodDbFragmentViewModel.class);
		viewModel.getAll().observe(this, new Observer<List<FoodItem>>()
		{
			@Override
			public void onChanged(List<FoodItem> foodItems)
			{
				foodItemAdapter.submitList(foodItems);
			}
		});

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
				viewModel.delete(toDelete);
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
}