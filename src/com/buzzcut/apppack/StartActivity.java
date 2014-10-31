package com.buzzcut.apppack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class StartActivity extends Activity {

	private static final String TAG = "StartActivity";

	LinearLayout shaver;
	Button shaverPowerButton;
	Vibrator vibe;
	MediaPlayer mediaPlayer = new MediaPlayer();
	boolean shaverPowerOn = false;
	boolean muted = false;
	boolean vibrationDisabled = false;
	long[] vibePattern = { 0, 10000, 1 };

	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // initialize
        shaver = (LinearLayout)findViewById(R.id.shaverBackground);
        shaverPowerButton = (Button)findViewById(R.id.shaverPowerButton);
        vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        
        
        shaverPowerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(shaverPowerOn == false)
				{
					shaverPowerButton.setBackgroundResource(R.drawable.button_back);
					
					if(vibrationDisabled == false)
					{
						// set Vibration
						vibe.vibrate(vibePattern, 0);
					}
					
					if(muted == false)
					{
						// sets up the mediaPlayer
						mediaPlayer.release();
						mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.buzz1);
						mediaPlayer.setLooping(true);
						mediaPlayer.start();
					}
					
					shaverPowerOn = true;
					Log.v(TAG, "6181POWER_ON");
				}
				else
				{
					shaverPowerButton.setBackgroundColor(Color.BLACK);
					
					// stops the Vibration
					vibe.cancel();
					
					// stops the media player
					mediaPlayer.stop();
					
					shaverPowerOn = false;
					Log.v(TAG, "6181POWER_OFF");
				}
			}
		});
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle the items
		switch (item.getItemId()) {
		case R.id.soundItem:
			if (muted == false) {
				muted = true;
				mediaPlayer.stop();
			} else {
				muted = false;
				if(shaverPowerOn == true)
				{
					// sets up the mediaPlayer
					mediaPlayer.release();
					mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.buzz1);
					mediaPlayer.setLooping(true);
					mediaPlayer.start();
				}
			}
			Log.v(TAG, "6181MUTE");
			return true;

		case R.id.vibrateItem:
			if (vibrationDisabled == false) {
				vibrationDisabled = true;
				vibe.cancel();
			} else {
				vibrationDisabled = false;
				if(shaverPowerOn == true)
				{
					// set Vibration
					vibe.vibrate(vibePattern, 0);
				}
			}
			Log.v(TAG, "6181VIBRATE");
			return true;

		case R.id.settingsItem:
			// opens the settings menu
			Intent intent = new Intent("com.buzzcut.apppack.SETTINGSACTIVITY");
			startActivity(intent);
			
			Log.v(TAG, "6181SETTINGS");
			return true;
			
		case R.id.quitItem:
			Log.v(TAG, "6181QUIT");
			mediaPlayer.stop();
			vibe.cancel();
			finish();
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// checks if shaver is muted
		if (muted == true) {
			MenuItem temp = menu.findItem(R.id.soundItem);
			temp.setTitle("Unmute");
		} else {
			MenuItem temp = menu.findItem(R.id.soundItem);
			temp.setTitle("Mute");
		}

		// checks if shaver's vibration is disabled
		if (vibrationDisabled == true) {
			MenuItem temp = menu.findItem(R.id.vibrateItem);
			temp.setTitle("Enable Vibrations");
		} else {
			MenuItem temp = menu.findItem(R.id.vibrateItem);
			temp.setTitle("Disable Vibrations");
		}

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(shaverPowerOn == true)
		{
			// stops the Vibration
			vibe.cancel();
			
			// stops the media player
			mediaPlayer.stop();
			
			shaverPowerButton.setBackgroundColor(Color.BLACK);
			
			shaverPowerOn = false;
		}
		super.onPause();
		
	}
	
	
	
}