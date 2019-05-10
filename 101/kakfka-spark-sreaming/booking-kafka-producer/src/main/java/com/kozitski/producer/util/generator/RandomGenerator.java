package com.kozitski.producer.util.generator;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * The type Random generator.
 */
@Component
public class RandomGenerator {
    private static final int SOURCE_DURATION = 365;
    private static final long DEFAULT_OFFSET = TimeUnit.MILLISECONDS.convert(SOURCE_DURATION, TimeUnit.DAYS);
    private static final Random random = new Random();

    /**
     * Generate random date date.
     *
     * @return the date
     */
    public Date generateRandomDate() {
        return new Date(generateRandomMillis());
    }

    /**
     * Generate random millis long.
     *
     * @return the long
     */
    public long generateRandomMillis() {
        return generateRandomMillis(DEFAULT_OFFSET);
    }

    /**
     * Generate random millis long.
     *
     * @param offset the offset
     * @return the long
     */
    public long generateRandomMillis(long offset) {
        long current = System.currentTimeMillis();
        return generateRandomLong(current - offset, current);
    }

    /**
     * Generate random long long.
     *
     * @param min the min
     * @param max the max
     * @return the long
     */
    public long generateRandomLong(long min, long max) {
        long result = NumberUtils.LONG_ZERO;

        if(random.longs(min, max).findFirst().isPresent()){
            result = random.longs(min, max).findFirst().getAsLong();
        }

        return result;
    }

    /**
     * Generate random int int.
     *
     * @param min the min
     * @param max the max
     * @return the int
     */
    public int generateRandomInt(int min, int max) {
        int result = NumberUtils.INTEGER_ZERO;

        if(random.ints(min, max).findFirst().isPresent()){
            result = random.ints(min, max).findFirst().getAsInt();
        }

        return result;
    }

    /**
     * Generate random double double.
     *
     * @param min the min
     * @param max the max
     * @return the double
     */
    public double generateRandomDouble(double min, double max) {
        double result = NumberUtils.DOUBLE_ZERO;

        if(random.doubles(min, max).findFirst().isPresent()){
            result = random.doubles(min, max).findFirst().getAsDouble();
        }

        return result;
    }

}
