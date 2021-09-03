package com.transienttetra.ezmacro.ui.dialogs;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;

import com.transienttetra.ezmacro.R;

public class WeightDialog
{
	private OnSaveListener listener;
	private AlertDialog.Builder dialogBuilder;
	private Context context;
	private final EditText editText;
	public interface OnSaveListener
	{
		public void onSave(WeightDialog weightDialog);
	}

	public WeightDialog(Context context, OnSaveListener listener)
	{
		this.listener = listener;
		dialogBuilder = new AlertDialog.Builder(context);
		editText = new EditText(context);
		editText.setHint(R.string.weight_dialog_hint);
		editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER);
		dialogBuilder.setTitle(R.string.weight_dialog_title);
		dialogBuilder.setView(editText);
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(editText);
		dialogBuilder.setView(layout);
		dialogBuilder.setPositiveButton(R.string.weight_dialog_save, (dialog, which) -> listener.onSave(this));
	}

	public float getWeight()
	{
		try
		{
			return Float.parseFloat(editText.getText().toString());
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	public void show()
	{
		dialogBuilder.show();
	}
}
