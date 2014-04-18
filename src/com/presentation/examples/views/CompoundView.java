package com.presentation.examples.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.presentation.examples.R;

/**
 * This is an efficient view that self manages the display of data and when used with a {@link ListView} also provides
 * an inherent ViewHolder pattern.
 * 
 * @see <a href="http://developer.android.com/guide/topics/ui/custom-components.html#compound">Android Compound
 *      Controls</a>
 */
public class CompoundView extends RelativeLayout {

	private TextView mTextViewOne;
	private TextView mTextViewTwo;

	public CompoundView(Context context) {
		super(context);
		init(context);
	}

	public CompoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CompoundView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public void updateView(int position) {
		mTextViewOne.setText(Integer.toString(position));
		mTextViewTwo.setText(Integer.toHexString(position));
	}

	private void init(Context context) {

		// Attach the inner TextViews from the layout
		LayoutInflater.from(context).inflate(R.layout.compound_view, this, true);

		// Memory reference the views (ViewHolder pattern)
		mTextViewOne = (TextView) findViewById(android.R.id.text1);
		mTextViewTwo = (TextView) findViewById(android.R.id.text2);
	}

}
