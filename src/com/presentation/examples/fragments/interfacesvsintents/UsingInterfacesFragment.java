package com.presentation.examples.fragments.interfacesvsintents;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;

import com.presentation.examples.R;

/**
 * Interfaces can provide tight coupling with your {@link Activity}. In this example, you actually end up with less code
 * in your {@link Fragment} implementation but at the same time you have now limited the mobility of this
 * implementation. If you wanted to reuse this in a future {@link Activity} or maybe even and entirely new project, you
 * will be forced to figure out how to re-implement this functionality. As such it may often be more forward thinking to
 * use intents to pass general concepts verses implementing logic and requirements directly in your activity.
 * <p>
 * It is also important to note that this implementation only requires knowledge of updating itself. If another fragment
 * needed to be updated based on the data manipulation from this fragment, your activity will need to know which
 * fragments to update and this may become cumbersome to your implementation.
 */
public class UsingInterfacesFragment extends ADataFragment {

	@Override
	public int getFragmentTitleResourceId() {
		return R.string.title_using_interfaces;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (!(activity instanceof IDataManagerListener))
			throw new IllegalArgumentException("Activity must implement " + IDataManagerListener.class.getSimpleName());
	}

	@Override
	protected ArrayList<String> getStrings() {
		return ((IDataManagerListener) getActivity()).getStrings();
	}

	@Override
	protected void addString(String string) {
		((IDataManagerListener) getActivity()).addString(string);
		updateAdater();
	}

	@Override
	protected void removeString() {
		((IDataManagerListener) getActivity()).removeString();
		updateAdater();
	}

}
