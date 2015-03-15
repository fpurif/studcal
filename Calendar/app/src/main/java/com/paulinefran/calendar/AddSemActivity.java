package com.paulinefran.calendar;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddSemActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addsem_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_sem, menu);
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
		else if(id == R.id.confirm){
			// put activity for confirm here
			
			EditText semTitleIn = (EditText) findViewById(R.id.semTitle);
			
			String semTitle = semTitleIn.getText().toString();
			
			if(semTitle.matches("") || semTitle.matches(" ")){
				Toast.makeText(getApplicationContext(), 
		                "NO INPUT ENTERED\n",
		                Toast.LENGTH_LONG).show();
			}
			else{
				Semester semester = new Semester(semTitle);
				
				// use "semesters" list from SemMenuActivity
				com.paulinefran.calendar.SemMenuActivity.semesters.addSemester(semester);
				
			    Toast.makeText(getApplicationContext(), 
		                "Sem entered : \n" + com.paulinefran.calendar.SemMenuActivity.semesters.head.title,
		                Toast.LENGTH_LONG).show();


			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
