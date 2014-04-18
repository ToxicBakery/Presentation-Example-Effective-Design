package com.presentation.examples.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Do not be afraid to build out common functionality. Repeated code throughout your application will lower your
 * cohesion and reduce its maintainability. Aim to be concise.
 */
public abstract class AListViewFragment extends ABaseFragment {

	/**
	 * Returns a new instance of {@link ABaseAdapter} to be attached to the fragments {@link ListView}.
	 * 
	 * @return
	 */
	protected abstract ABaseAdapter getAdapterInstance();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final ListView view = new ListView(getActivity());
		view.setAdapter(getAdapterInstance());

		return view;
	}

	protected static abstract class ABaseAdapter extends BaseAdapter {

		protected final Context mContext;

		public ABaseAdapter(Context context) {
			mContext = context;
		}

		@Override
		public int getCount() {
			return 1000;
		}

		@Override
		public Integer getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

}
