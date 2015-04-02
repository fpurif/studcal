package paulinefran.studentcalendar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import paulinefran.studentcalendar.R;
import paulinefran.studentcalendar.ListSemestersAdapter;
import paulinefran.studentcalendar.SemesterDAO;
import paulinefran.studentcalendar.Semester;

public class ListSemestersActivity extends Activity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener, View.OnClickListener {

    public static final String TAG = "ListSemestersActivity";

    public static final int REQUEST_CODE_ADD_SEMESTER = 40;
    public static final String EXTRA_ADDED_SEMESTER = "extra_key_added_semester";

    private ListView mListviewSemesters;
    private TextView mTxtEmptyListSemesters;
    private ImageButton mBtnAddSemester;
    private ImageButton mBtnGotoCalendar;

    private ListSemestersAdapter mAdapter;
    private List<Semester> mListSemesters;
    private SemesterDAO mSemesterDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_semesters);

        // initialize views
        initViews();

        // fill the listView
        mSemesterDao = new SemesterDAO(this);
        mListSemesters = mSemesterDao.getAllSemesters();
        if(mListSemesters != null && !mListSemesters.isEmpty()) {
            mAdapter = new ListSemestersAdapter(this, mListSemesters);
            mListviewSemesters.setAdapter(mAdapter);
        }
        else {
            mTxtEmptyListSemesters.setVisibility(View.VISIBLE);
            mListviewSemesters.setVisibility(View.GONE);
        }
    }

    private void initViews() {
        this.mListviewSemesters = (ListView) findViewById(R.id.list_semesters);
        this.mTxtEmptyListSemesters = (TextView) findViewById(R.id.txt_empty_list_semesters);
        this.mBtnAddSemester = (ImageButton) findViewById(R.id.btn_add_semester);
        this.mBtnGotoCalendar = (ImageButton) findViewById(R.id.btn_goto_calendar);
        this.mListviewSemesters.setOnItemClickListener(this);
        this.mListviewSemesters.setOnItemLongClickListener(this);
        this.mBtnAddSemester.setOnClickListener(this);
        this.mBtnGotoCalendar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_semester:
                Intent intent = new Intent(this, AddSemesterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_SEMESTER);
                break;

            case R.id.btn_goto_calendar:
                intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Check requestCode: ", Integer.toString(requestCode));
        if(requestCode == REQUEST_CODE_ADD_SEMESTER) {
            if(resultCode == RESULT_OK) {
                // add the added semester to the listSemesters and refresh the listView
                if(data != null) {
                    Semester createdSemester = (Semester) data.getSerializableExtra(EXTRA_ADDED_SEMESTER);
                    if(createdSemester != null) {
                        if(mListSemesters == null)
                            mListSemesters = new ArrayList<Semester>();
                        mListSemesters.add(createdSemester);

                        if(mListviewSemesters.getVisibility() != View.VISIBLE) {
                            mListviewSemesters.setVisibility(View.VISIBLE);
                            mTxtEmptyListSemesters.setVisibility(View.GONE);
                        }

                        if(mAdapter == null) {
                            mAdapter = new ListSemestersAdapter(this, mListSemesters);
                            mListviewSemesters.setAdapter(mAdapter);
                        }
                        else {
                            mAdapter.setItems(mListSemesters);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSemesterDao.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Semester clickedSemester = mAdapter.getItem(position);
        Log.d(TAG, "clickedItem : " + clickedSemester.getName());
        Intent intent = new Intent(this, ListCoursesActivity.class);
        intent.putExtra(ListCoursesActivity.EXTRA_SELECTED_SEMESTER_ID, clickedSemester.getId());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Semester clickedSemester = mAdapter.getItem(position);
        Log.d(TAG, "longClickedItem : "+clickedSemester.getName());
        showDeleteDialogConfirmation(clickedSemester);
        return true;
    }

    private void showDeleteDialogConfirmation(final Semester clickedSemester) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder.setMessage("Are you sure you want to delete the \""+clickedSemester.getName()+"\" semester ?");

        // set positive button YES message
        alertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // delete the semester and refresh the list
                if(mSemesterDao != null) {
                    mSemesterDao.deleteSemester(clickedSemester);
                    mListSemesters.remove(clickedSemester);

                    //refresh the listView
                    if(mListSemesters.isEmpty()) {
                        mListSemesters = null;
                        mListviewSemesters.setVisibility(View.GONE);
                        mTxtEmptyListSemesters.setVisibility(View.VISIBLE);
                    }
                    mAdapter.setItems(mListSemesters);
                    mAdapter.notifyDataSetChanged();
                }

                dialog.dismiss();
                Toast.makeText(ListSemestersActivity.this, R.string.semester_deleted_successfully, Toast.LENGTH_SHORT).show();
            }
        });

        // set neutral button OK
        alertDialogBuilder.setNeutralButton(android.R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }
}
