package com.presentation.examples;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.presentation.examples.fragments.ABaseFragment;
import com.presentation.examples.fragments.compoundviews.ListViewWithCompoundViewFragment;
import com.presentation.examples.fragments.compoundviews.ListViewWithoutCompoundViewFragment;
import com.presentation.examples.fragments.interfacesvsintents.UsingIntentsFragment;
import com.presentation.examples.fragments.interfacesvsintents.UsingInterfacesFragment;

/**
 * A standardish implementation of an Android Navigation Drawer. Nothing exciting here.
 */
public class NavigationDrawerFragment extends Fragment {

	private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

	private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

	private ActionBarDrawerToggle mDrawerToggle;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerListView;
	private View mFragmentContainerView;

	private int mCurrentSelectedPosition = 0;
	private boolean mFromSavedInstanceState;
	private boolean mUserLearnedDrawer;

	public NavigationDrawerFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
		mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

		if (savedInstanceState != null) {
			mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
			mFromSavedInstanceState = true;
		}

		selectItem(mCurrentSelectedPosition, true);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mDrawerListView = (ListView) inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
		mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectItem(position);
			}
		});
		mDrawerListView.setAdapter(new NavigationAdapter(getActionBar().getThemedContext()));
		mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
		return mDrawerListView;
	}

	public boolean isDrawerOpen() {
		return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
	}

	/**
	 * Users of this fragment must call this method to set up the navigation drawer interactions.
	 * 
	 * @param fragmentId
	 *            The android:id of this fragment in its activity's layout.
	 * @param drawerLayout
	 *            The DrawerLayout containing this fragment's UI.
	 */
	public void setUp(int fragmentId, DrawerLayout drawerLayout) {
		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.navigation_drawer_open, /* "open drawer" description for accessibility */
		R.string.navigation_drawer_close /* "close drawer" description for accessibility */
		) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				if (!isAdded()) {
					return;
				}

				getActivity().invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				if (!isAdded()) {
					return;
				}

				if (!mUserLearnedDrawer) {
					mUserLearnedDrawer = true;
					SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
					sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
				}

				getActivity().invalidateOptionsMenu();
			}
		};

		if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
			mDrawerLayout.openDrawer(mFragmentContainerView);
		}

		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private void selectItem(int position) {
		selectItem(position, false);
	}

	private void selectItem(int position, boolean firstRun) {
		mCurrentSelectedPosition = position;
		if (mDrawerListView != null)
			mDrawerListView.setItemChecked(position, true);

		if (mDrawerLayout != null)
			mDrawerLayout.closeDrawer(mFragmentContainerView);

		final ABaseFragment fragment;
		if (mDrawerListView != null) {
			fragment = ((NavigationAdapter) mDrawerListView.getAdapter()).getNewFragmentInstance(position);
		} else {
			fragment = new ListViewWithoutCompoundViewFragment();
		}

		// Show the fragment
		final String tag = getString(fragment.getFragmentTitleResourceId());
		final FragmentTransaction ft = getFragmentManager().beginTransaction();
		if (!firstRun)
			ft.addToBackStack(tag);
		ft.replace(R.id.container, fragment, tag);
		ft.commit();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		if (mDrawerLayout != null && isDrawerOpen())
			showGlobalContextActionBar();

		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item))
			return true;

		return super.onOptionsItemSelected(item);
	}

	private void showGlobalContextActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setTitle(R.string.app_name);
	}

	private ActionBar getActionBar() {
		return getActivity().getActionBar();
	}

	private static final class NavigationAdapter extends BaseAdapter {

		// @formatter:off
		private static final NavItemData[] NAV_ITEMS = new NavItemData[] {
			new NavItemData(ListViewWithoutCompoundViewFragment.class, new ListViewWithoutCompoundViewFragment().getFragmentTitleResourceId())
			, new NavItemData(ListViewWithCompoundViewFragment.class, new ListViewWithCompoundViewFragment().getFragmentTitleResourceId())
			, new NavItemData(UsingInterfacesFragment.class, new UsingInterfacesFragment().getFragmentTitleResourceId())
			, new NavItemData(UsingIntentsFragment.class, new UsingIntentsFragment().getFragmentTitleResourceId())
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
			} catch (java.lang.InstantiationException | IllegalAccessException e) {
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

}
