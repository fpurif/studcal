package com.paulinefran.calendar;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SemMenuActivity extends ActionBarActivity {

	public static SemesterList semesters;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sem_menu);
		Intent intent = getIntent();
		semesters = new SemesterList();
		initializeSemList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sem_menu, menu);
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
		else if(id == R.id.addSem){
			Intent intent = new Intent(this, AddSemActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}

	public void initializeSemList(){
        // display semester list here
		Semester head = semesters.getSemList();
		
		// put code that makes list of semesters from SemesterList linked list
		
		
		/*
		final ListView listview = (ListView) findViewById(R.id.listView);
		
		// turn semester list into array of titles
		String [] values = new String[10];
		int x = 0;
		Semester current = head;
		Semester next = current.next;
		while(current != null){
			values[x] = current.title;
			current = next;
			next = current.next;
		}
		/*
		// COULD BE PROBLEM??
		// code taken from: http://www.vogella.com/tutorials/AndroidListView/article.html#androidlists_overview
		final ArrayList<String> list = new ArrayList<String>();
		for (int i=0; i < values.length; ++i){
			list.add(values[i]);
		}
		
		final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position,
					long id) {
				final String item = (String) parent.getItemAtPosition(position);
				view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable(){

					@Override
					public void run() {
						list.remove(item);
						adapter.notifyDataSetChanged();
						view.setAlpha(1);
					}
					
				});
				
			}
			
		}); */
		
		}
	


}
