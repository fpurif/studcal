package paulinefran.studentcalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import paulinefran.studentcalendar.R;
import paulinefran.studentcalendar.ListCoursesAdapter;
import paulinefran.studentcalendar.CourseDAO;
import paulinefran.studentcalendar.Course;

public class ListCoursesActivity extends Activity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener, View.OnClickListener {

    public static final String TAG = "ListCoursesActivity";

    public static final int REQUEST_CODE_ADD_COURSE = 40;
    public static final String EXTRA_ADDED_COURSE = "extra_key_added_course";
    public static final String EXTRA_SELECTED_SEMESTER_ID = "extra_key_selected_semester_id";

    private ListView mListviewCourses;
    private TextView mTxtEmptyListCourses;
    private ImageButton mBtnAddCourse;

    private ListCoursesAdapter mAdapter;
    private List<Course> mListCourses;
    private CourseDAO mCourseDao;

    private long mSemesterId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_courses);

        // initialize views
        initViews();

        // get the semester id from extras
        mCourseDao = new CourseDAO(this);
        Intent intent  = getIntent();
        if(intent != null) {
            this.mSemesterId = intent.getLongExtra(EXTRA_SELECTED_SEMESTER_ID, -1);
        }

        if(mSemesterId != -1) {
            mListCourses = mCourseDao.getCoursesOfSemester(mSemesterId);
            // fill the listView
            if(mListCourses != null && !mListCourses.isEmpty()) {
                mAdapter = new ListCoursesAdapter(this, mListCourses);
                mListviewCourses.setAdapter(mAdapter);
            }
            else {
                mTxtEmptyListCourses.setVisibility(View.VISIBLE);
                mListviewCourses.setVisibility(View.GONE);
            }
        }
    }

    private void initViews() {
        this.mListviewCourses = (ListView) findViewById(R.id.list_courses);
        this.mTxtEmptyListCourses = (TextView) findViewById(R.id.txt_empty_list_courses);
        this.mBtnAddCourse = (ImageButton) findViewById(R.id.btn_add_course);
        this.mListviewCourses.setOnItemClickListener(this);
        this.mListviewCourses.setOnItemLongClickListener(this);
        this.mBtnAddCourse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_course:
                Intent intent = new Intent(this, AddCourseActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_COURSE);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_ADD_COURSE) {
            if(resultCode == RESULT_OK) {
                //refresh the listView
                if(mListCourses == null || !mListCourses.isEmpty()) {
                    mListCourses = new ArrayList<Course>();
                }

                if(mCourseDao == null)
                    mCourseDao = new CourseDAO(this);
                mListCourses = mCourseDao.getCoursesOfSemester(mSemesterId);

                if(mListCourses != null && !mListCourses.isEmpty() &&
                        mListviewCourses.getVisibility() != View.VISIBLE) {
                    mTxtEmptyListCourses.setVisibility(View.GONE);
                    mListviewCourses.setVisibility(View.VISIBLE);
                }

                if(mAdapter == null) {
                    mAdapter = new ListCoursesAdapter(this, mListCourses);
                    mListviewCourses.setAdapter(mAdapter);
                }
                else {
                    mAdapter.setItems(mListCourses);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCourseDao.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Course clickedCourse = mAdapter.getItem(position);
        Log.d(TAG, "clickedItem : " + clickedCourse.getCName());
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Course clickedCourse = mAdapter.getItem(position);
        Log.d(TAG, "longClickedItem : "+clickedCourse.getCName());

        showDeleteDialogConfirmation(clickedCourse);
        return true;
    }

    private void showDeleteDialogConfirmation(final Course course) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder
                .setMessage("Are you sure you want to delete the course \""
                        + course.getCName() + "\"");

        // set positive button YES message
        alertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // delete the course and refresh the list
                if(mCourseDao != null) {
                    mCourseDao.deleteCourse(course);

                    //refresh the listView
                    mListCourses.remove(course);
                    if(mListCourses.isEmpty()) {
                        mListviewCourses.setVisibility(View.GONE);
                        mTxtEmptyListCourses.setVisibility(View.VISIBLE);
                    }

                    mAdapter.setItems(mListCourses);
                    mAdapter.notifyDataSetChanged();
                }

                dialog.dismiss();
                Toast.makeText(ListCoursesActivity.this, R.string.course_deleted_successfully, Toast.LENGTH_SHORT).show();

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

