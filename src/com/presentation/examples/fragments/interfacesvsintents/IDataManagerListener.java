package com.presentation.examples.fragments.interfacesvsintents;

import java.util.ArrayList;

public interface IDataManagerListener {

	/**
	 * Get a shallow copy of all the Strings
	 * 
	 * @return
	 */
	public ArrayList<String> getStrings();

	/**
	 * Add a string to the data set.
	 * 
	 * @param string
	 */
	public void addString(String string);

	/**
	 * Remove the last added String
	 */
	public void removeString();

}