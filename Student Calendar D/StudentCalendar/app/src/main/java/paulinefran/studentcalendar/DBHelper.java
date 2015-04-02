package paulinefran.studentcalendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBHelper";

    // columns of the SEMESTERS table
    public static final String TABLE_SEMESTERS = "semesters";
    public static final String COLUMN_SEMESTER_ID = "_id";
    public static final String COLUMN_SEMESTER_NAME = "semester_name";

    // columns of the COURSES table
    public static final String TABLE_COURSES = "courses";
    public static final String COLUMN_COURSE_ID = COLUMN_SEMESTER_ID;
    public static final String COLUMN_COURSE_NAME = "course_name";
    public static final String COLUMN_COURSE_LOCATION = "course_location";
    public static final String COLUMN_COURSE_INSTRUCTOR = "course_instructor";
    public static final String COLUMN_COURSE_INFO = "course_info";
    public static final String COLUMN_COURSE_SEMESTER_ID = "semester_id";

    private static final String DATABASE_NAME = "semesters.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement of the courses table creation
    private static final String SQL_CREATE_TABLE_COURSES = "CREATE TABLE " + TABLE_COURSES + "("
            + COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COURSE_NAME + " TEXT NOT NULL, "
            + COLUMN_COURSE_LOCATION + " TEXT NOT NULL, "
            + COLUMN_COURSE_INSTRUCTOR + " TEXT NOT NULL, "
            + COLUMN_COURSE_INFO + " TEXT NOT NULL, "
            + COLUMN_COURSE_SEMESTER_ID + " INTEGER NOT NULL);";

    // SQL statement of the companies table creation
    private static final String SQL_CREATE_TABLE_SEMESTERS = "CREATE TABLE " + TABLE_SEMESTERS + "("
            + COLUMN_SEMESTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SEMESTER_NAME + " TEXT NOT NULL);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_SEMESTERS);
        database.execSQL(SQL_CREATE_TABLE_COURSES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEMESTERS);

        // recreate the tables
        onCreate(db);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
}
