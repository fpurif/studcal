package com.paulinefran.calendar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class CustomOnItemSelectedListener implements OnItemSelectedListener{
	
	// CALL OTHER ACTIVITIES FOR DIFF VIEWS HERE
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
            long id) {

        /*Toast.makeText(parent.getContext(),
                "On Item Select : \n" + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_LONG).show(); */

        if (new String("By Day").equals(parent.getItemAtPosition(pos).toString())) {
            //Insert Daily view here
            Toast.makeText(parent.getContext(),
                    "By Day TEST",
                    Toast.LENGTH_LONG).show();
        }
        else if (new String("By Week").equals(parent.getItemAtPosition(pos).toString())) {
            //Weekly view
                Toast.makeText(parent.getContext(),
                        "By Week TEST",
                        Toast.LENGTH_LONG).show();
            }
        else{
            //Monthly view
                Toast.makeText(parent.getContext(),
                        "By Month TEST",
                        Toast.LENGTH_LONG).show();
                }


    }
 
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }
}
