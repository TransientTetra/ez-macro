package com.transienttetra.ezmacro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezmacro.R;

import java.util.ArrayList;
import java.util.List;

public class FoodItemAdapter extends ListAdapter<FoodItem, FoodItemAdapter.ViewHolder>
{
	private OnItemClickListener listener;
	private static final DiffUtil.ItemCallback<FoodItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<FoodItem>()
	{
		@Override
		public boolean areItemsTheSame(@NonNull FoodItem oldItem, @NonNull FoodItem newItem)
		{
			return oldItem.getId() == newItem.getId();
		}

		@Override
		public boolean areContentsTheSame(@NonNull FoodItem oldItem, @NonNull FoodItem newItem)
		{
			return oldItem.equals(newItem);
		}
	};

	public FoodItemAdapter()
	{
		super(DIFF_CALLBACK);
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		Context context = parent.getContext();
		LayoutInflater layoutInflater = LayoutInflater.from(context);

		View view = layoutInflater.inflate(R.layout.food_item, parent, false);

		ViewHolder viewHolder = new ViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position)
	{
		FoodItem foodItem = getItem(position);
		TextView foodName = holder.foodName;
		foodName.setText(foodItem.getName());
	}

	public FoodItem getFoodItemAt(int position)
	{
		return getItem(position);
	}

	public class ViewHolder extends RecyclerView.ViewHolder
	{
		public TextView foodName;

		public ViewHolder(@NonNull View itemView)
		{
			super(itemView);
			foodName = itemView.findViewById(R.id.foodName);

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
		void onItemClick(FoodItem foodItem);
	}

	public void setOnItemClickListener(OnItemClickListener listener)
	{
		this.listener = listener;
	}
}
