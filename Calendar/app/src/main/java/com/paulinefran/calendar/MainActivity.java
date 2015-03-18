package com.paulinefran.calendar;


import android.provider.CalendarContract;
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
import android.widget.Button;

import java.util.GregorianCalendar;

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
            //ADD EVENT
            Toast.makeText(getApplicationContext(),
                    "ADD EVENT TEST",
                    Toast.LENGTH_LONG).show();
            Intent calIntent = new Intent(Intent.ACTION_INSERT);
            calIntent.setType("vnd.android.cursor.item/event");
            calIntent.putExtra(CalendarContract.Events.TITLE, "Title Example");
            calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Location Example");
            calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Description Example");

            GregorianCalendar calDate = new GregorianCalendar(2015, 3, 26);
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                    calDate.getTimeInMillis());
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                    calDate.getTimeInMillis());

            startActivity(calIntent);
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
	
	/* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	Following code for onlongclick on month doesn't work, see above for alternative
	 */
	public void initializeCalendar() {
		CalendarView calendarmonth = (CalendarView) findViewById(R.id.calendarmonth);

        //press and hold listener in month
        calendarmonth.setOnLongClickListener(
                new CalendarView.OnLongClickListener(){
                    public boolean OnLongClick(View CalendarView){
                        Toast.makeText(getApplicationContext(),
                                "HI",
                                Toast.LENGTH_LONG).show();
                        return true;
                    }

                    @Override
                    public boolean onLongClick(View CalendarView) {
                        return false;
                    }
                }
        );
    }

		

}
	