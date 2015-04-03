package paulinefran.studentcalendar;

import java.io.Serializable;

public class Semester implements Serializable {

    public static final String TAG = "Course";
    private static final long serialVersionUID = -7406082437623008161L;

    private long mId;
    private String mName;

    public Semester() {}

    public Semester(String name) {
        if (name == null)
            this.mName = "";
        else
            this.mName = name;
    }

    public long getId(){
        return mId;
    }

    public void setId(long mId){
        this.mId = mId;
    }

    public String getName(){
        return mName;
    }

    public void setName(String mSemName){
        if(mSemName == null)
            return;
        this.mName = mSemName;
    }
}