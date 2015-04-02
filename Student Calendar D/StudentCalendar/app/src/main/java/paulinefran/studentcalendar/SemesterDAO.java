package paulinefran.studentcalendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import paulinefran.studentcalendar.Semester;
import paulinefran.studentcalendar.Course;

public class SemesterDAO {

    public static final String TAG = "SemesterDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = { DBHelper.COLUMN_SEMESTER_ID,
            DBHelper.COLUMN_SEMESTER_NAME};

    public SemesterDAO(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on opening database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
        Log.d("Insert Database Check", "DATABASE OPENED");
    }

    public void close() {
        mDbHelper.close();
    }

    public Semester createSemester(String name) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_SEMESTER_NAME, name);
        Log.d("Insert Database Check", name);

        long insertId = mDatabase
                .insert(DBHelper.TABLE_SEMESTERS, null, values);

        Cursor cursor = mDatabase.query(DBHelper.TABLE_SEMESTERS, mAllColumns,
                DBHelper.COLUMN_SEMESTER_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Semester newSemester = cursorToSemester(cursor);
        cursor.close();
        return newSemester;
    }

    public void deleteSemester(Semester semester) {
        long id = semester.getId();
        // delete all courses of this company
        CourseDAO courseDao = new CourseDAO(mContext);
        List<Course> listCourses = courseDao.getCoursesOfSemester(id);
        if (listCourses != null && !listCourses.isEmpty()) {
            for (Course e : listCourses) {
                courseDao.deleteCourse(e);
            }
        }

        System.out.println("the deleted company has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_SEMESTERS, DBHelper.COLUMN_SEMESTER_ID
                + " = " + id, null);
    }

    public List<Semester> getAllSemesters() {
        List<Semester> listSemesters = new ArrayList<Semester>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_SEMESTERS, mAllColumns,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Semester semester = cursorToSemester(cursor);
                listSemesters.add(semester);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listSemesters;
    }

    public Semester getSemesterById(long id) {
        Cursor cursor = mDatabase.query(DBHelper.TABLE_SEMESTERS, mAllColumns,
                DBHelper.COLUMN_SEMESTER_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Semester semester = cursorToSemester(cursor);
        return semester;
    }

    protected Semester cursorToSemester(Cursor cursor) {
        Semester semester = new Semester();
        semester.setId(cursor.getLong(0));
        semester.setName(cursor.getString(1));
        return semester;
    }

}
