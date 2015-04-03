package paulinefran.studentcalendar.test;

import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

/**
 * Created by fjpp on 02/04/2015.
 */
public class SampleTest extends InstrumentationTestCase {
    AndroidTestCase tester = new AndroidTestCase();

    public void test() throws Exception{
        int expected = 1;
        int reality = 5;
        tester.assertEquals(expected,reality);
    }
}
