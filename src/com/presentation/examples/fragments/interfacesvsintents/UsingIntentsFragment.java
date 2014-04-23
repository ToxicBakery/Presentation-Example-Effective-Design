package com.presentation.examples.fragments.interfacesvsintents;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.presentation.examples.R;

/**
 * Listening to an update notification, this {@link Fragment} is now able to know, and let others know, about direct or
 * indirect modification to data. Using a more robust set of defined Actions and {@link Bundle} Extras, an application
 * can interact easily with any other piece of the application or even interact with entirely different applications.
 * <p>
 * {@link Intent}s are not as fast as interfaces and are definitely not something you want send extremely often such as
 * every frame of game. They are however still quite fast and the Android OS can easily handle hundreds of
 * {@link Intent}s a second without causing much overhead to your application.
 */
public class UsingIntentsFragment extends ADataFragment {

	@Override
	public int getFragmentTitleResourceId() {
		return R.string.title_using_intents;
	}

	@Override
	public void onResume() {
		super.onResume();

		getActivity().registerReceiver(mUpdateReceiver, new IntentFilter(StaticDataContainer.ACTION_UPDATE));
	}

	@Override
	public void onPause() {
		super.onPause();

		getActivity().unregisterReceiver(mUpdateReceiver);
	}

	@Override
	protected ArrayList<String> getStrings() {
		return StaticDataContainer.getInstance().getStrings();
	}

	@Override
	protected void addString(String string) {
		StaticDataContainer.getInstance().addString(string);
		getActivity().sendBroadcast(new Intent(StaticDataContainer.ACTION_UPDATE));
	}

	@Override
	protected void removeString() {
		StaticDataContainer.getInstance().removeString();
		getActivity().sendBroadcast(new Intent(StaticDataContainer.ACTION_UPDATE));
	}

	private final BroadcastReceiver mUpdateReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			updateAdater();
		}
	};

}
