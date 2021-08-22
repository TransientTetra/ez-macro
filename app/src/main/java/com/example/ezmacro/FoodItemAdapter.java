package com.example.ezmacro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder>
{
	private List<FoodItem> foodItemList = new ArrayList<>();
	private OnItemClickListener listener;

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
		FoodItem foodItem = foodItemList.get(position);
		TextView foodName = holder.foodName;
		foodName.setText(foodItem.getName());
	}

	@Override
	public int getItemCount()
	{
		return foodItemList.size();
	}

	public void setFoodItemList(List<FoodItem> foodItemList)
	{
		this.foodItemList = foodItemList;
		notifyDataSetChanged();
	}

	public FoodItem getFoodItemAt(int position)
	{
		return foodItemList.get(position);
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
						listener.onItemClick(foodItemList.get(position));
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
