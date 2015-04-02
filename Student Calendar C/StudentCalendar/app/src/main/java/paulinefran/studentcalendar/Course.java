package paulinefran.studentcalendar;

import java.io.Serializable;


public class Course implements Serializable {

    public static final String TAG = "Course";
    private static final long serialVersionUID = -7406082437623008161L;

    private long mId;
    private String mCName;
    private String mLocation;
    private String mColor; //CHANGE LATER
    private String mInstructor;
    private String mInfo;
    private Semester mSemester;

    public Course() {

    }

    public Course(String course, String location, String color, String instructor, String info) {
        this.mCName = course;
        this.mLocation = location;
        this.mColor = color;
        this.mInstructor = instructor;
        this.mInfo = info;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getCName() {
        return mCName;
    }

    public void setCName(String mCName) {
        this.mCName = mCName;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String mColor) {
        this.mColor = mColor;
    }

    public String getInstructor() {
        return mInstructor;
    }

    public void setInstructor(String mInstructor) {
        this.mInstructor = mInstructor;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String mInfo) {
        this.mInfo = mInfo;
    }

    public Semester getmSemester() {
        return mSemester;
    }

    public void setSemester(Semester mSemester) {
        this.mSemester = mSemester;
    }
}
