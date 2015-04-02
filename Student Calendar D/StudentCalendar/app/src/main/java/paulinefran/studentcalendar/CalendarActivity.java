package paulinefran.studentcalendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;

import java.util.Calendar;


public class CalendarActivity extends Activity implements View.OnClickListener {

    private ImageButton mBtnAddEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initViews();
    }


    private void initViews() {
        this.mBtnAddEvent = (ImageButton) findViewById(R.id.btn_add_event);
        this.mBtnAddEvent.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_event:
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2015, 3, 26, 7, 30);
                Calendar endTime = Calendar.getInstance();
                endTime.set(2015, 3, 26, 8, 30);
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                        .putExtra(CalendarContract.Events.TITLE, "")
                        .putExtra(CalendarContract.Events.DESCRIPTION, "")
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, "")
                        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                        .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

