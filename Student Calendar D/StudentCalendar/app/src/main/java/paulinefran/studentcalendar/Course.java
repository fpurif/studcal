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
        if(mId < 0) {
            this.mId = 0;
            return;
        }
        this.mId = mId;
    }

    public String getCName() {
        return mCName;
    }

    public void setCName(String mCName) {
        if(mCName == null)
        {
            this.mCName = "";
            return;
        }
        this.mCName = mCName;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        if(mLocation == null) {
            this.mLocation = "none";
            return;
        }
        this.mLocation = mLocation;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String mColor) {

        if(mColor == null)
        {
            this.mColor = "white";
            return;
        }this.mColor = mColor;
    }

    public String getInstructor() {
        return mInstructor;
    }

    public void setInstructor(String mInstructor) {
        if(mInstructor == null) {
            this.mInstructor = "none";
            return;
        }
        this.mInstructor = mInstructor;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String mInfo) {
        if(mInfo == null)
        {
            this.mInfo = "none";
            return;}
        this.mInfo = mInfo;
    }

    public Semester getmSemester() {
        return mSemester;
    }

    public void setSemester(Semester mSemester) {

        this.mSemester = mSemester;
    }
}
