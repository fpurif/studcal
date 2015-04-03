package paulinefran.studentcalendar.test;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

import paulinefran.studentcalendar.Course;
import paulinefran.studentcalendar.DBHelper;
import paulinefran.studentcalendar.Semester;
import paulinefran.studentcalendar.SemesterDAO;

/**
 * Created by fjpp on 02/04/2015.
 */
public class TestCases extends InstrumentationTestCase {
    AndroidTestCase tester = new AndroidTestCase();

    ////////// TEST SEMESTER CLASS /////////////////
    // Test Semester class getName() method normal input
    public void testSemester_getName_Normal() {
        // setup
        Semester semesterA = new Semester("Winter");



        // actual
        String actual = semesterA.getName();


        tester.assertEquals("Winter", actual);
    }

    // Test Semester class getName() method null input
    public void testSemester_getName_Null() {
        // setup
        Semester semesterA = new Semester(null);



        // actual
        String actual = semesterA.getName();


        tester.assertEquals("", actual);
    }

    // Test Semester class setName() method normal input
    public void testSemester_setName_Normal() {
        // setup
        Semester semesterA = new Semester("X");
        semesterA.setName("NewName");


        // actual
        String actual = semesterA.getName();

        tester.assertEquals("NewName", actual);
    }

    // Test Semester class setName() method null input
    public void testSemester_setName_Null() {
        // setup
        Semester semesterA = new Semester("X");

        // act
        semesterA.setName(null);


        // actual
        String actual = semesterA.getName();

        tester.assertEquals("X", actual);
    }

    // Test Semester class setId() method null input
    public void testSemester_setId_Normal() {
        // setup
        Semester semesterA = new Semester();

        // act
        semesterA.setId(1);


        // actual
        long actual = semesterA.getId();

        tester.assertEquals(1, actual);
    }

    //////////// TEST COURSE CLASS /////////////

    // setId() method negative input
    public void testCourse_setId_Negative() {
        // setup
        Course course = new Course();

        // act
        course.setId(-1);


        // actual
        long actual = course.getId();

        tester.assertEquals(0, actual);
    }

    // setId() method normal input
    public void testCourse_setId_Normal() {
        // setup
        Course course = new Course();

        // act
        course.setId(1);


        // actual
        long actual = course.getId();

        tester.assertEquals(1, actual);
    }

    // setCName() method normal input
    public void testCourse_setCName_Normal() {
        // setup
        Course course = new Course();

        // act
        course.setCName("Math");


        // actual
        String actual = course.getCName();

        tester.assertEquals("Math", actual);
    }

    // setCName() method null input
    public void testCourse_setCName_Null() {
        // setup
        Course course = new Course();

        // act
        course.setCName(null);


        // actual
        String actual = course.getCName();

        tester.assertEquals("", actual);
    }

    // setLocation() method normal input
    public void testCourse_setLocation_Normal() {
        // setup
        Course course = new Course();

        // act
        course.setLocation("MacHall");


        // actual
        String actual = course.getLocation();

        tester.assertEquals("MacHall", actual);
    }

    // setLocation() method null input
    public void testCourse_setLocation_Null() {
        // setup
        Course course = new Course();

        // act
        course.setLocation(null);


        // actual
        String actual = course.getLocation();

        tester.assertEquals("none", actual);
    }

    // setColor() method normal input
    public void testCourse_setColor_Normal() {
        // setup
        Course course = new Course();

        // act
        course.setColor("white");


        // actual
        String actual = course.getColor();

        tester.assertEquals("white", actual);
    }

    // setLocation() method null input
    public void testCourse_setColor_Null() {
        // setup
        Course course = new Course();

        // act
        course.setColor(null);


        // actual
        String actual = course.getColor();

        tester.assertEquals("white", actual);
    }

    // setInstructor() method null input
    public void testCourse_setInstructor_Normal() {
        // setup
        Course course = new Course();

        // act
        course.setInstructor("Mr. Dixon Cider");


        // actual
        String actual = course.getInstructor();

        tester.assertEquals("Mr. Dixon Cider", actual);
    }

    // setInstructor() method null input
    public void testCourse_setInstructor_Null() {
        // setup
        Course course = new Course();

        // act
        course.setInstructor(null);


        // actual
        String actual = course.getInstructor();

        tester.assertEquals("none", actual);
    }

    // setInstructor() method null input
    public void testCourse_setInfo_Normal(){
        // setup
        Course course = new Course();

        // act
        course.setInfo("Install Android Studio");


        // actual
        String actual = course.getInfo();

        tester.assertEquals("Install Android Studio", actual);
    }

    // setInstructor() method null input
    public void testCourse_setInfo_Null() {
        // setup
        Course course = new Course();

        // act
        course.setInfo(null);


        // actual
        String actual = course.getInfo();

        tester.assertEquals("none", actual);
    }

    // setInstructor() method null input
    public void testCourse_setSemester_Normal(){
        // setup
        Course course = new Course();
        Semester sem = new Semester();

        // act
        course.setSemester(sem);


        // actual
        Semester actual = course.getmSemester();

        tester.assertEquals(sem, actual);
    }

    // setInstructor() method null input
    public void testCourse_setSem_Null() {
        // setup
        Course course = new Course();

        // act
        course.setSemester(null);


        // actual
        Semester actual = course.getmSemester();

        tester.assertEquals(null, actual);
    }
}
