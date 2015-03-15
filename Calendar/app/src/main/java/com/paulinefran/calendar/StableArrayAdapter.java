package com.paulinefran.calendar;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;



// code taken from: http://www.vogella.com/tutorials/AndroidListView/article.html#androidlists_overview
public class StableArrayAdapter extends ArrayAdapter<String>{  
	
	HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
	
	public StableArrayAdapter(Context context, int resource,
			List<String> objects) {
		super(context, resource, objects);
	      for (int i = 0; i < objects.size(); ++i) {
	          mIdMap.put(objects.get(i), i);
	      }
	}
	 @Override
	    public long getItemId(int position) {
	      String item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds() {
	      return true;
	    }
}