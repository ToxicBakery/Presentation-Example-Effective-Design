package com.presentation.examples.fragments.interfacesvsintents;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.presentation.examples.R;
import com.presentation.examples.fragments.ABaseFragment;

public abstract class ADataFragment extends ABaseFragment implements OnClickListener {

	private Random mRand;
	private Button mButtonAddString;
	private Button mButtonRemoveString;
	private ArrayAdapter<String> mAdapter;

	protected abstract ArrayList<String> getStrings();

	protected abstract void addString(String string);

	protected abstract void removeString();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mRand = new Random();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_data_editing, container, false);

		mButtonAddString = (Button) view.findViewById(R.id.fragment_data_editing_button_add_string);
		mButtonRemoveString = (Button) view.findViewById(R.id.fragment_data_editing_button_remove_string);

		mButtonAddString.setOnClickListener(this);
		mButtonRemoveString.setOnClickListener(this);

		final ListView listView = (ListView) view.findViewById(android.R.id.list);
		mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
		listView.setAdapter(mAdapter);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		
		updateAdater();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_data_editing_button_add_string:
			final StringBuffer sb = new StringBuffer();
			for (int i = 0; i < 10; i++)
				sb.append((char) (65 + mRand.nextInt(90 - 65)));

			addString(sb.toString());
			break;
		case R.id.fragment_data_editing_button_remove_string:
			removeString();
			break;
		}
	}

	protected final void updateAdater() {
		mAdapter.clear();
		mAdapter.addAll(getStrings());
		mAdapter.notifyDataSetChanged();
	}

}
