package com.presentation.examples.fragments;

import android.content.Context;
import android.widget.BaseAdapter;

public abstract class AListViewFragment extends ABaseFragment {

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
