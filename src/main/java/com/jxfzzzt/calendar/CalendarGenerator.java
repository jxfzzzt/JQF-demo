package com.jxfzzzt.calendar;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.util.Calendar.*;

// 待测试对象生成器
public class CalendarGenerator extends Generator<GregorianCalendar> {

    public CalendarGenerator() {
        super(GregorianCalendar.class);
    }

    @Override
    public GregorianCalendar generate(SourceOfRandomness randomness, GenerationStatus generationStatus) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setLenient(true); // This allows invalid dates to silently wrap (e.g. Apr 31 ==> May 1).

        // Randomly pick a day, month, and year
        cal.set(DAY_OF_MONTH, randomness.nextInt(31) + 1); // a number between 1 and 31 inclusive
        cal.set(MONTH, randomness.nextInt(12) + 1); // a number between 1 and 12 inclusive
        cal.set(YEAR, randomness.nextInt(cal.getMinimum(YEAR), cal.getMaximum(YEAR)));

        // Optionally also pick a time
        if (randomness.nextBoolean()) {
            cal.set(HOUR, randomness.nextInt(24));
            cal.set(MINUTE, randomness.nextInt(60));
            cal.set(SECOND, randomness.nextInt(60));
        }

        // Let's set a timezone
        // First, get supported timezone IDs (e.g. "America/Los_Angeles")
        String[] allTzIds = TimeZone.getAvailableIDs();

        // Next, choose one randomly from the array
        String tzId = randomness.choose(allTzIds);
        TimeZone tz = TimeZone.getTimeZone(tzId);

        // Assign it to the calendar
        cal.setTimeZone(tz);

        // Return the randomly generated calendar object
        return cal;
    }
}