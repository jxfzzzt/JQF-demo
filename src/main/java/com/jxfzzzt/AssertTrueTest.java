package com.jxfzzzt;
import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.Assume.*;


@RunWith(JQF.class)
public class AssertTrueTest {

    @Fuzz
    public void testAssertTrue(Integer x) {
        assumeTrue(x >= 0);
        assertTrue("Error Occur", check(x));
    }

    public static Boolean check(Integer x) {
        Integer a = 10 / x;
        return true;
    }
}