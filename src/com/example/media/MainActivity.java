package com.example.media;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {
	public TextView songName,startTimeField,endTimeField;
	private MediaPlayer mediaPlayer;
	private long startTime = 0;
	private long finalTime = 0;
	private Handler myHandler = new Handler();;
	private int forwardTime = 5000; 
	private int backwardTime = 5000;
	private SeekBar seekbar;
	private Button playButton,pauseButton,stopbutton,close;
	public static int oneTimeOnly = 0;
	private int[] songs = new int[3];
	String time;
	int currenttrack=1;
	Uri audioFileUri;
	boolean br;
	 TranslateAnimation animation,animation1,animation2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media);
		songs[0] = R.raw.song;
	//	mediaPlayer=new MediaPlayer();
		
		
		  
		  
		  
	seekbar = (SeekBar)findViewById(R.id.seekBar);
		playButton = (Button)findViewById(R.id.buttonPlay);
		pauseButton = (Button)findViewById(R.id.buttonpause);
		stopbutton=(Button)findViewById(R.id.buttonStop);
		startTimeField=(TextView)findViewById(R.id.textViewCurrentTime);
		endTimeField=(TextView)findViewById(R.id.TextViewDuration);
		seekbar.setClickable(true);
		pauseButton.setEnabled(false);
		songName=(TextView)findViewById(R.id.textViewFileName);
		
		stopbutton.setEnabled(false);
		mediaPlayer = MediaPlayer.create(this,R.raw.song );
		songName.setText("song.mp3");
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
		{   int progress = 0;
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				progress = arg1;
				if(arg2==true)
				{
			
				mediaPlayer.seekTo(progress);			 
			}
			}
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	} 
	
	public void animations(){

	    ImageView image = (ImageView)findViewById(R.id.imageView2);
	    ImageView image1 = (ImageView)findViewById(R.id.imageView1);
	    ImageView img_animation = (ImageView) findViewById(R.id.img_animation);
		 
		  /*TranslateAnimation animation = new TranslateAnimation(0.0f, 00.0f,
		    00.0f, 0.0f);
		  TranslateAnimation animation1 = new TranslateAnimation(0.0f, 00.0f,
				    100.0f, 220.0f);
		  TranslateAnimation animation2 = new TranslateAnimation(0.0f, 0.0f,
				    290.0f, 220.0f);*/
	     animation = new TranslateAnimation(0.0f, 00.0f,
			    -04.0f, 120.0f);
			   animation1 = new TranslateAnimation(0.0f, 00.0f,
					    -04.0f, 140.0f);
			   animation2 = new TranslateAnimation(0.0f, 0.0f,
					    -04.0f,180.0f);
		  animation.setDuration(5000);
		  animation.setRepeatCount(5);
		  animation.setRepeatMode(2);
		  animation.setFillAfter(true);
		  animation1.setDuration(5000);
		  animation1.setRepeatCount(5);
		  animation1.setRepeatMode(2);
		  animation1.setFillAfter(true);
		  animation2.setDuration(5000);
		  animation2.setRepeatCount(5);
		  animation2.setRepeatMode(2);
		  animation2.setFillAfter(true);
		  img_animation.startAnimation(animation);
		  
	  // Animation AnimationMovepos = AnimationUtils.loadAnimation(this, R.anim.vert); 
	    image.startAnimation(animation1); 
	    image1.startAnimation(animation2); 

	    }



public void close()
{
	System.exit(1);
}
public void browse()
{   br=true;
	Intent i = new Intent();
	i.setAction(Intent.ACTION_GET_CONTENT);
	i.setType("audio/mpeg");
	startActivityForResult(Intent.createChooser(i, "yo"), 1);
	
}@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult( requestCode, resultCode,  data);
	if (requestCode == 1 && resultCode == Activity.RESULT_OK){
		if(mediaPlayer.isPlaying())
		{
			stopp();
		}
		
        if ((data != null) && (data.getData() != null)){
            audioFileUri = data.getData();
            
            playButton.performClick();
            // Now you can use that Uri to get the file path, or upload it, ...
            }
        }
	
}
public void stopp()
{
	Toast.makeText(getApplicationContext(), "stopped", 
			Toast.LENGTH_SHORT).show();
	
	 animation1.cancel();
	 animation.cancel();
	 animation2.cancel();

	oneTimeOnly = 0;
	pauseButton.setEnabled(true);
	playButton.setEnabled(true);
	mediaPlayer.seekTo(0);
	mediaPlayer.stop();
	
}
	public void stop(View view){
		
        
stopp();
	}

	public void pause(View view){
		Toast.makeText(getApplicationContext(), "Pausing sound", 
				Toast.LENGTH_SHORT).show();

		mediaPlayer.pause();
		pauseButton.setEnabled(false);
		playButton.setEnabled(true);
		 animation1.cancel();
		 animation.cancel();
		 animation2.cancel();
	}	
	@SuppressLint("NewApi")
	public void forward(View view){
		int temp = (int)startTime;
		if((temp+forwardTime)<=finalTime){
			startTime = startTime + forwardTime;
			mediaPlayer.seekTo((int) startTime);
		}


	}
	public void rewind(View view){
		int temp = (int)startTime;
		if((temp-backwardTime)>0){
			startTime = startTime - backwardTime;
			mediaPlayer.seekTo((int) startTime);
		}


	}
	@SuppressLint("NewApi")
	public void play(View view){
/*		Toast.makeText(getApplicationContext(), "Playing sound", 
				Toast.LENGTH_SHORT).show();*/
		
		animations();
			try {if(br==true){
	
				//mediaPlayer = MediaPlayer.create(this,audioFileUri.getPath() );
				Toast.makeText(getApplicationContext(), audioFileUri.getPath() , 
						Toast.LENGTH_SHORT).show();
				//songName.setText(audioFileUri.getLastPathSegment());
				}
			else
			{   
				mediaPlayer = MediaPlayer.create(this,R.raw.song );
				songName.setText("song.mp3");
			}
				try {
					mediaPlayer.prepare();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		mediaPlayer.start();
		finalTime = mediaPlayer.getDuration();
		startTime = mediaPlayer.getCurrentPosition();
		
		endTimeField.setText(String.format("%d : %d ", 
			    TimeUnit.MILLISECONDS.toMinutes(finalTime),
			    TimeUnit.MILLISECONDS.toSeconds(finalTime) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(finalTime))));
		
			seekbar.setMax((int) finalTime);
			oneTimeOnly = 1;
		 
		seekbar.setProgress((int)startTime);
		myHandler.postDelayed(UpdateSongTime,100);
		pauseButton.setEnabled(true);
		stopbutton.setEnabled(true);
		playButton.setEnabled(false);
	}





	private Runnable UpdateSongTime = new Runnable() {
		public void run() {
			startTime = mediaPlayer.getCurrentPosition();
			time=String.format("%d : %d ", 
				    TimeUnit.MILLISECONDS.toMinutes(startTime),
				    TimeUnit.MILLISECONDS.toSeconds(startTime) - 
				    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startTime))
				);
			startTimeField.setText(time);
			seekbar.setProgress((int)startTime);
			myHandler.postDelayed(this, 100);
		}
	};




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.browse) {
			browse();
		}
		if (id == R.id.close) {
			close();
		}
		return super.onOptionsItemSelected(item);
	}
}
