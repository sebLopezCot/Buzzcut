package com.buzzcut.apppack;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SettingsActivity extends ListActivity {

	String classes[] = { ".ContactDeveloper", ".AboutProduct" };
	String classNames[] = { "Talk to Developer", "About" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(SettingsActivity.this, android.R.layout.simple_list_item_1, classNames));
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		String classSelected = classes[position];
		
		super.onListItemClick(l, v, position, id);
		try{
			Class newClass = Class.forName("com.buzzcut.apppack" + classSelected);
			startActivity(new Intent(SettingsActivity.this, newClass));
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
}
