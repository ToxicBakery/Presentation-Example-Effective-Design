package com.presentation.examples;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.presentation.examples.fragments.interfacesvsintents.IDataManagerListener;
import com.presentation.examples.fragments.interfacesvsintents.StaticDataContainer;

/**
 * A clean and simple Activity can help reduce your code complexity. Activities should help tie fragments together and
 * possibly control generally logic.
 * <p>
 * Avoid using activities for performing data manipulation. Additionally, avoid making your Fragments dependent on your
 * Activity or visa-versa. Failure to do so can cause headaches later on when major design changes occur.
 */
public class MainActivity extends Activity implements IDataManagerListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer)).setUp(
				R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

	/*
	 * The following code is specific to the UsingInterfacesFragment example.
	 */

	@Override
	public ArrayList<String> getStrings() {
		return StaticDataContainer.getInstance().getStrings();
	}

	@Override
	public void addString(String string) {
		StaticDataContainer.getInstance().addString(string);
	}

	@Override
	public void removeString() {
		StaticDataContainer.getInstance().removeString();
	}

}
