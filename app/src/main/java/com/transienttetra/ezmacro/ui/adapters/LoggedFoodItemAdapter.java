package com.transienttetra.ezmacro.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.transienttetra.ezmacro.R;
import com.transienttetra.ezmacro.entities.FoodItem;
import com.transienttetra.ezmacro.relations.LoggedFoodItem;
import com.transienttetra.ezmacro.entities.Nutrition;
import com.transienttetra.ezmacro.util.EnergyConverter;

public class LoggedFoodItemAdapter extends ListAdapter<LoggedFoodItem, LoggedFoodItemAdapter.ViewHolder>
{
	private OnItemClickListener listener;
	private static final DiffUtil.ItemCallback<LoggedFoodItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<LoggedFoodItem>()
	{
		@Override
		public boolean areItemsTheSame(@NonNull LoggedFoodItem oldItem, @NonNull LoggedFoodItem newItem)
		{
			return oldItem.getId() == newItem.getId();
		}

		@Override
		public boolean areContentsTheSame(@NonNull LoggedFoodItem oldItem, @NonNull LoggedFoodItem newItem)
		{
			return oldItem.getFoodItem().equals(newItem.getFoodItem());
		}
	};

	public LoggedFoodItemAdapter()
	{
		super(DIFF_CALLBACK);
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		Context context = parent.getContext();
		LayoutInflater layoutInflater = LayoutInflater.from(context);

		View view = layoutInflater.inflate(R.layout.logged_food_item, parent, false);

		return new ViewHolder(view);
	}

	@SuppressLint("DefaultLocale")
	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position)
	{
		FoodItem foodItem = getItem(position).getFoodItem();

		holder.foodName.setText(foodItem.getName());
		Nutrition nutrition = foodItem.getNutrition();
		// todo temporary fixed kcal
		holder.energy.setText(String.format("%1.0f kcal", EnergyConverter.jouleToKcal(nutrition.getEnergy())));
		holder.protein.setText(String.format("%1.1f p", nutrition.getProtein()));
		holder.fat.setText(String.format("%1.1f f", nutrition.getFats()));
		holder.carb.setText(String.format("%1.1f c", nutrition.getCarbohydrates()));
		holder.weight.setText(String.format("%1.1f g", getItem(position).getLoggedWeight()));
	}

	public LoggedFoodItem getLoggedFoodItemAt(int adapterPosition)
	{
		return getItem(adapterPosition);
	}

	public class ViewHolder extends RecyclerView.ViewHolder
	{
		public TextView foodName;
		public TextView energy;
		public TextView protein;
		public TextView fat;
		public TextView carb;
		public TextView weight;

		public ViewHolder(@NonNull View itemView)
		{
			super(itemView);
			foodName = itemView.findViewById(R.id.foodName);
			energy = itemView.findViewById(R.id.energyText);
			protein = itemView.findViewById(R.id.proteinText);
			fat = itemView.findViewById(R.id.fatText);
			carb = itemView.findViewById(R.id.carbText);
			weight = itemView.findViewById(R.id.weightText);

			itemView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					int position = getAdapterPosition();
					if (listener != null && position != RecyclerView.NO_POSITION)
						listener.onItemClick(getItem(position));
				}
			});
		}
	}

	public interface OnItemClickListener
	{
		void onItemClick(LoggedFoodItem foodItem);
	}

	public void setOnItemClickListener(OnItemClickListener listener)
	{
		this.listener = listener;
	}
}
