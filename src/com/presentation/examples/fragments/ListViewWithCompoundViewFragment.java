package com.presentation.examples.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.presentation.examples.R;
import com.presentation.examples.views.CompoundView;

public class ListViewWithCompoundViewFragment extends AListViewFragment {

	@Override
	public int getFragmentTitleResourceId() {
		return R.string.title_listview_with_compound_view;
	}

	@Override
	protected ABaseAdapter getAdapterInstance() {
		return new BasicAdapter(getActivity());
	}

	/**
	 * This adapter is loosely coupled to the view. If in the future the {@link CompoundView} needed to be modified,
	 * this adapter will likely live on untouched.
	 */
	protected static final class BasicAdapter extends ABaseAdapter {

		public BasicAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null)
				convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_listview_compound_item, null);

			((CompoundView) convertView).updateView(getItem(position));

			return convertView;
		}

	}

}
