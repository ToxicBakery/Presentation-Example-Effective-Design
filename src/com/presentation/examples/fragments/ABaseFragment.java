package com.presentation.examples.fragments;

import android.app.Fragment;

/**
 * An abstract base fragment is a powerful tool for enforcing design throughout your application. The most common need
 * in fragments is retrieving a TAG and Title for the fragment. This base class provides a convenient method of allowing
 * all fragments to generically identify themselves.
 */
public abstract class ABaseFragment extends Fragment {

	/**
	 * Returns the Android String Resource ID for the fragment implementation name.
	 * 
	 * @return
	 */
	public abstract int getFragmentTitleResourceId();

}
