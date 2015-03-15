package com.paulinefran.calendar;


import android.support.v4.app.NotificationCompat;
import android.support.v7.app.*;
import android.view.Menu;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	CalendarView calendar;
	Spinner viewOptions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeCalendar();
		addListenerOnSpinnerItemSelection();
		
	}

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
		if (id == R.id.action_settings) {

			return true;
		}
		else if(id == R.id.semOption){
			Intent intent = new Intent(this, SemMenuActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	// add spinner data
	public void addListenerOnSpinnerItemSelection(){
		viewOptions = (Spinner) findViewById(R.id.viewDrop1);
		viewOptions.setOnItemSelectedListener(new CustomOnItemSelectedListener()) ;
		
	}
	
	/*private OnLongClickListener dateListener = new OnLongClickListener(){

		@Override
		public boolean onLongClick(View v) {
			
			return true;
		}
		
	};*/
	
	
	public void initializeCalendar() {
		calendar = (CalendarView) findViewById(R.id.calendarmonth);
		//calendar.setOnLongClickListener(dateListener);
		
		
			
		};
		

			}
	