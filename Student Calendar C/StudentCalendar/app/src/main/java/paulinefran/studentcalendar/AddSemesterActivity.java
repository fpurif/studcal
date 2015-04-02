package paulinefran.studentcalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import paulinefran.studentcalendar.R;
import paulinefran.studentcalendar.SemesterDAO;
import paulinefran.studentcalendar.Semester;

public class AddSemesterActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "AddSemesterActivity";

    private EditText mTxtSemesterName;
    private Button mBtnAdd;

    private SemesterDAO mSemesterDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);

        initViews();

        this.mSemesterDao = new SemesterDAO(this);
    }

    private void initViews() {
        this.mTxtSemesterName = (EditText) findViewById(R.id.txt_semester_name);
        this.mBtnAdd = (Button) findViewById(R.id.btn_add);

        this.mBtnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Editable semesterName = mTxtSemesterName.getText();
                if (!TextUtils.isEmpty(semesterName)) {
                    // add the semester to database
                    Semester createdSemester = mSemesterDao.createSemester(semesterName.toString());

                    Log.d(TAG, "added semester : " + createdSemester.getName());
                    Intent intent = new Intent();
                    intent.putExtra(ListSemestersActivity.EXTRA_ADDED_SEMESTER, createdSemester);
                    setResult(RESULT_OK, intent);
                    Toast.makeText(this, R.string.semester_created_successfully, Toast.LENGTH_LONG).show();
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
}
