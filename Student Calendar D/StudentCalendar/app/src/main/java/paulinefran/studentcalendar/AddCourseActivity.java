package paulinefran.studentcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import paulinefran.studentcalendar.R;
import paulinefran.studentcalendar.SpinnerSemestersAdapter;
import paulinefran.studentcalendar.SemesterDAO;
import paulinefran.studentcalendar.CourseDAO;
import paulinefran.studentcalendar.Semester;
import paulinefran.studentcalendar.Course;

public class AddCourseActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static final String TAG = "AddCourseActivity";

    private EditText mTxtCourseName;
    private EditText mTxtLocation;
    private EditText mTxtInstructor;
    private EditText mTxtInformation;
    private Spinner mSpinnerSemester;
    private Button mBtnAdd;

    private SemesterDAO mSemesterDao;
    private CourseDAO mCourseDao;

    private Semester mSelectedSemester;
    private SpinnerSemestersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        initViews();

        this.mSemesterDao = new SemesterDAO(this);
        this.mCourseDao = new CourseDAO(this);

        //fill the spinner with semesters
        List<Semester> listSemesters = mSemesterDao.getAllSemesters();
        if(listSemesters != null) {
            mAdapter = new SpinnerSemestersAdapter(this, listSemesters);
            mSpinnerSemester.setAdapter(mAdapter);
            mSpinnerSemester.setOnItemSelectedListener(this);
        }
    }

    private void initViews() {
        this.mTxtCourseName = (EditText) findViewById(R.id.txt_course_name);
        this.mTxtLocation = (EditText) findViewById(R.id.txt_location);
        this.mTxtInstructor = (EditText) findViewById(R.id.txt_instructor);
        this.mTxtInformation = (EditText) findViewById(R.id.txt_information);
        this.mSpinnerSemester = (Spinner) findViewById(R.id.spinner_semesters);
        this.mBtnAdd = (Button) findViewById(R.id.btn_add);

        this.mBtnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Editable courseName = mTxtCourseName.getText();
                Editable location = mTxtLocation.getText();
                Editable instructor = mTxtInstructor.getText();
                Editable information = mTxtInformation.getText();
                mSelectedSemester = (Semester) mSpinnerSemester.getSelectedItem();
                if (!TextUtils.isEmpty(courseName) && !TextUtils.isEmpty(location)
                        && !TextUtils.isEmpty(instructor)
                        && mSelectedSemester != null
                        && !TextUtils.isEmpty(information)) {
                    // add the course to database
                    Course createdCourse = mCourseDao.createCourse(courseName.toString(), instructor.toString(), location.toString(), information.toString(), mSelectedSemester.getId());
                    Toast.makeText(this, R.string.course_created_successfully, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "added course : " + createdCourse.getCName() + " " + createdCourse.getInstructor() + ", course.semesterId : " + createdCourse.getmSemester().getId());
                    setResult(RESULT_OK);
                    finish();

                }
                else {
                    Toast.makeText(this, R.string.empty_fields_message, Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSemesterDao.close();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSelectedSemester = mAdapter.getItem(position);
        Log.d(TAG, "selectedSemester : "+mSelectedSemester.getName());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
