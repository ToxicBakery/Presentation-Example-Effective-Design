package com.presentation.examples;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

/**
 * A clean and simple Activity can help reduce your code complexity. Activities should help tie fragments together and
 * possibly control generally logic.
 * <p>
 * Avoid using activities for performing data manipulation. Additionally, avoid making your Fragments dependent on your
 * Activity or visa-versa. Failure to do so can cause headaches later on when major design changes occur.
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer)).setUp(
				R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

}
