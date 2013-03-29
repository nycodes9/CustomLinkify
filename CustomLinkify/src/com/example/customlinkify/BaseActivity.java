package com.example.customlinkify;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.BufferType;

public class BaseActivity extends Activity {

	private TextView mTV;
	private SpannableString mSpanStr;
	private CustomClickableSpan mClickSpanA;
	private CustomClickableSpan mClickSpanC;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_base);

		mTV = (TextView) findViewById(R.id.baseTV);		// added textSelectable=true in xml
		
		mSpanStr = new SpannableString("ClickableA nonclickableB DifferentClickableC");
		
		mClickSpanA = new CustomClickableSpan();
		mClickSpanC = new CustomClickableSpan();
		
		mSpanStr.setSpan(mClickSpanA, 0, "ClickableA".length(), SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);
		mSpanStr.setSpan(mClickSpanC, 
				mSpanStr.length() - "DifferentClickableC".length(),  mSpanStr.length(), 
				SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);
		
		mTV.setText(mSpanStr, BufferType.SPANNABLE);
		mTV.setMovementMethod(LinkMovementMethod.getInstance());
	}

	static class CustomClickableSpan extends ClickableSpan {

		@Override
		public void onClick(View view) {
			Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void updateDrawState(TextPaint ds) {
			super.updateDrawState(ds);
			ds.setUnderlineText(false);		//removed underline
			ds.setColor(Color.CYAN);			// custom text color
			//ds.drawableState						// nearest thing to Color State List, but no docs 
		}
		
	}
}
