package com.buzzcut.apppack;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class AboutProduct extends Activity{

	TextView titleText, bodyText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_product);
		
		// Init
		titleText = (TextView)findViewById(R.id.aboutProductTitleText);
		bodyText = (TextView)findViewById(R.id.aboutProductBodyText);
		
		// changes text color
		titleText.setTextColor(Color.YELLOW);
		bodyText.setTextColor(Color.YELLOW);
		
		// changes the body text
		bodyText.setText("This is an app that lets you cut your hair. It is funny to play pranks with.");
	}
	
}
