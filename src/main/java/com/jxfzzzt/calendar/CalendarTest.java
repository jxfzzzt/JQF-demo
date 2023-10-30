package com.jxfzzzt.calendar;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.generator.Size;
import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Calendar.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

// 待测试的类逻辑方法
class CalendarLogic {
    // Returns true iff cal is in a leap year
    public static boolean isLeapYear(GregorianCalendar cal) {
        int year = cal.get(YEAR);
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return false;
            }
            return true;
        }
        return false;
    }

    // Returns either of -1, 0, 1 depending on whether c1 is <, =, > than c2
    public static int compare(GregorianCalendar c1, GregorianCalendar c2) {
        int cmp;
        cmp = Integer.compare(c1.get(YEAR), c2.get(YEAR));
        if (cmp == 0) {
            cmp = Integer.compare(c1.get(MONTH), c2.get(MONTH));
            if (cmp == 0) {
                cmp = Integer.compare(c1.get(DAY_OF_MONTH), c2.get(DAY_OF_MONTH));
                if (cmp == 0) {
                    cmp = Integer.compare(c1.get(HOUR), c2.get(HOUR));
                    if (cmp == 0) {
                        cmp = Integer.compare(c1.get(MINUTE), c2.get(MINUTE));
                        if (cmp == 0) {
                            cmp = Integer.compare(c1.get(SECOND), c2.get(SECOND));
                            if (cmp == 0) {
                                cmp = Integer.compare(c1.get(MILLISECOND), c2.get(MILLISECOND));
                            }
                        }
                    }
                }
            }
        }
        return cmp;
    }
}


// 测试主方法
@RunWith(JQF.class)
public class CalendarTest {
    // 需要先把failure corpus生成出来
    @Fuzz(repro = "target/classes/fuzz-results/failures/id_000000")
    public void testLeapYear(@From(CalendarGenerator.class) GregorianCalendar calendar) {
        assumeTrue(calendar.get(MONTH) == FEBRUARY);
        assumeTrue(calendar.get(DAY_OF_MONTH) == 29);

        assertTrue(calendar.get(YEAR) + " should be a leap year", CalendarLogic.isLeapYear(calendar));
    }

    @Fuzz
    public void testCompare(@Size(max = 100) List<@From(CalendarGenerator.class) GregorianCalendar> calendarList) {
        Collections.sort(calendarList, CalendarLogic::compare);

        for (int i = 1; i < calendarList.size(); i++) {
            GregorianCalendar cal1 = calendarList.get(i - 1);
            GregorianCalendar cal2 = calendarList.get(i);
            assumeFalse(cal1.equals(cal2));
            assertTrue(cal1 + " should be before " + cal1, cal1.before(cal2));
        }
    }

    public static void main(String[] args) {

    }
}
