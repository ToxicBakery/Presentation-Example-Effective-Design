package com.presentation.examples.fragments.interfacesvsintents;

import java.util.ArrayList;

public final class StaticDataContainer implements IDataManagerListener {

	public static final String ACTION_UPDATE = StaticDataContainer.class.getPackage() + ".ACTION_UPDATE";

	private static final ArrayList<String> STRING_DATA = new ArrayList<String>();

	private static final StaticDataContainer mContainer = new StaticDataContainer();

	private StaticDataContainer() {
	}

	public static final StaticDataContainer getInstance() {
		return mContainer;
	}

	@Override
	public ArrayList<String> getStrings() {
		return new ArrayList<String>(STRING_DATA);
	}

	@Override
	public final void addString(String string) {
		STRING_DATA.add(string);
	}

	@Override
	public final void removeString() {
		if (STRING_DATA.size() > 0)
			STRING_DATA.remove(STRING_DATA.size() - 1);
	}

}
