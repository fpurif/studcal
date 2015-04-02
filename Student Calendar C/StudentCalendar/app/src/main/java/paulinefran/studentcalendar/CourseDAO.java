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

public class CourseDAO {

    public static final String TAG = "CourseDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.COLUMN_COURSE_ID, DBHelper.COLUMN_COURSE_NAME,
            DBHelper.COLUMN_COURSE_LOCATION, DBHelper.COLUMN_COURSE_INSTRUCTOR,
            DBHelper.COLUMN_COURSE_INFO, DBHelper.COLUMN_COURSE_SEMESTER_ID };

    public CourseDAO(Context context) {
        mDbHelper = new DBHelper(context);
        this.mContext = context;
        // open the database
        try {
            open();
        }
        catch(SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Course createCourse(String courseName, String instructor, String location, String information, long semesterId) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_COURSE_NAME, courseName);
        values.put(DBHelper.COLUMN_COURSE_LOCATION, location);
        values.put(DBHelper.COLUMN_COURSE_INSTRUCTOR, instructor);
        values.put(DBHelper.COLUMN_COURSE_INFO, information);
        values.put(DBHelper.COLUMN_COURSE_SEMESTER_ID, semesterId);
        long insertId = mDatabase.insert(DBHelper.TABLE_COURSES, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_COURSES,
                mAllColumns, DBHelper.COLUMN_COURSE_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Course newCourse = cursorToCourse(cursor);
        cursor.close();
        return newCourse;
    }

    public void deleteCourse(Course course) {
        long id = course.getId();
        System.out.println("the deleted course has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_COURSES, DBHelper.COLUMN_COURSE_ID + " = " + id, null);
    }

    public List<Course> getAllCourses() {
        List<Course> listCourses = new ArrayList<Course>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_COURSES,
                mAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = cursorToCourse(cursor);
            listCourses.add(course);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listCourses;
    }

    public List<Course> getCoursesOfSemester(long companyId) {
        List<Course> listCourses = new ArrayList<Course>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_COURSES, mAllColumns
                , DBHelper.COLUMN_COURSE_SEMESTER_ID + " = ?",
                new String[] { String.valueOf(companyId) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = cursorToCourse(cursor);
            listCourses.add(course);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listCourses;
    }

    private Course cursorToCourse(Cursor cursor) {
        Course course = new Course();
        course.setId(cursor.getLong(0));
        course.setCName(cursor.getString(1));
        course.setLocation(cursor.getString(2));
        course.setColor(cursor.getString(3));
        course.setInstructor(cursor.getString(4));
        course.setInfo(cursor.getString(5));

        // get The company by id
        long semesterId = cursor.getLong(6);
        SemesterDAO dao = new SemesterDAO(mContext);
        Semester semester = dao.getSemesterById(semesterId);
        if(semester != null)
            course.setSemester(semester);

        return course;
    }

}


