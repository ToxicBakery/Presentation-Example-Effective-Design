package com.presentation.examples.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.presentation.examples.R;

public class ListViewWithoutCompoundViewFragment extends AListViewFragment {

	@Override
	public int getFragmentTitleResourceId() {
		return R.string.title_listview_without_compound_view;
	}

	@Override
	protected ABaseAdapter getAdapterInstance() {
		return new BasicAdapter(getActivity());
	}

	/**
	 * This adapter is tightly coupled to the view. If in the future the view needed to be modified, this adapter and
	 * any other uses of the view will need to be updated directly.
	 */
	private static final class BasicAdapter extends ABaseAdapter {

		public BasicAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;

			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.textViewOne.setText(Integer.toString(position));
			holder.textViewTwo.setText(Integer.toHexString(position));

			return convertView;
		}

		/**
		 * A simple ViewHolder pattern implementation.
		 * 
		 * @see <a
		 *      href="http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder">Android
		 *      ViewHolder Pattern</a>
		 */
		static final class ViewHolder {

			final TextView textViewOne;
			final TextView textViewTwo;

			ViewHolder(View convertView) {
				textViewOne = (TextView) convertView.findViewById(android.R.id.text1);
				textViewTwo = (TextView) convertView.findViewById(android.R.id.text2);
			}
		}

	}

}
