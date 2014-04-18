package com.presentation.examples.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.presentation.examples.fragments.ABaseFragment;
import com.presentation.examples.fragments.ListViewWithCompoundViewFragment;
import com.presentation.examples.fragments.ListViewWithoutCompoundViewFragment;

public final class NavigationAdapter extends BaseAdapter {

	// @formatter:off
	private static final NavItemData[] NAV_ITEMS = new NavItemData[] {
		new NavItemData(ListViewWithoutCompoundViewFragment.class, new ListViewWithoutCompoundViewFragment().getFragmentTitleResourceId())
		, new NavItemData(ListViewWithCompoundViewFragment.class, new ListViewWithCompoundViewFragment().getFragmentTitleResourceId())
	};
	// @formatter:on

	private final LayoutInflater mInflater;

	public NavigationAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return NAV_ITEMS.length;
	}

	@Override
	public NavItemData getItem(int postion) {
		return NAV_ITEMS[postion];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		final NavItemData navItem = getItem(position);
		final NavItemView navItemView;

		if (convertView == null) {
			convertView = mInflater.inflate(android.R.layout.simple_list_item_1, null);
			navItemView = new NavItemView(convertView);
			convertView.setTag(navItemView);
		} else {
			navItemView = (NavItemView) convertView.getTag();
		}

		navItemView.updateView(navItem);

		return convertView;
	}

	public ABaseFragment getNewFragmentInstance(int position) {
		try {
			return getItem(position).mFragmentClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException();
		}
	}

	private static final class NavItemData {

		private final Class<? extends ABaseFragment> mFragmentClass;
		private final int mNameResourceId;

		NavItemData(Class<? extends ABaseFragment> fragmentClass, int nameResourceId) {
			mFragmentClass = fragmentClass;
			mNameResourceId = nameResourceId;
		}

	}

	private static final class NavItemView {

		private final TextView mTextView;

		NavItemView(View convertView) {
			mTextView = (TextView) convertView.findViewById(android.R.id.text1);
		}

		void updateView(NavItemData navItem) {
			mTextView.setText(navItem.mNameResourceId);
		}

	}

}
