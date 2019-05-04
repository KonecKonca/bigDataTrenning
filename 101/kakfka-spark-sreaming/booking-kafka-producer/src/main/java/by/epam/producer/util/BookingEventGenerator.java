package by.epam.producer.util;

import by.epam.producer.entity.BookingEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BookingEventGenerator {

    private static final long DEFAULT_OFFSET = TimeUnit.MILLISECONDS.convert(365, TimeUnit.DAYS);

    private final SimpleDateFormat DATE_TIME_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final SimpleDateFormat SRCH_CI_CO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final Random random = new Random();

    /**
     * Generates {@link BookingEvent} object with random data.
     *
     * @return {@link BookingEvent} object.
     */
    public BookingEvent generate() {
        BookingEvent event = new BookingEvent();
        event.setDateTime(DATE_TIME_DATE_FORMAT.format(generateRandomDate()));
        event.setSiteName(generateRandomInt(1, 15));
        event.setPosaContinent(generateRandomInt(1, 7));
        event.setUserLocationCountry(generateRandomInt(1, 196));
        event.setUserLocationRegion(generateRandomInt(1, 1001));
        event.setUserLocationCity(generateRandomInt(1, 50001));
        event.setOrigDestinationDistance(generateRandomDouble(0, 10000));
        event.setUserId(generateRandomInt(1, 1000000));
        event.setIsMobile(generateRandomInt(0, 2));
        event.setIsPackage(generateRandomInt(0, 2));
        event.setChannel(generateRandomInt(1, 1001));
        event.setSrchCi(SRCH_CI_CO_DATE_FORMAT.format(generateRandomDate()));
        event.setSrchCo(SRCH_CI_CO_DATE_FORMAT.format(generateRandomDate()));
        event.setSrchAdultsCnt(generateRandomInt(1, 10));
        event.setSrchChildrenCnt(generateRandomInt(1, 5));
        event.setSrchRmCnt(generateRandomInt(1, 4));
        event.setSrchDestinationId(generateRandomInt(1, 50001));
        event.setSrchDestinationTypeId(generateRandomInt(1, 10));
        event.setHotelContinent(generateRandomInt(1, 7));
        event.setHotelCountry(generateRandomInt(1, 196));
        event.setHotelMarket(generateRandomInt(1, 2000));
        event.setHotelCluster(generateRandomInt(1, 100));

        return event;
    }

    private Date generateRandomDate() {
        return new Date(generateRandomMillis());
    }

    private Date generateRandomDate(long offset) {
        return new Date(generateRandomMillis(offset));
    }

    private long generateRandomMillis() {
        return generateRandomMillis(DEFAULT_OFFSET);
    }

    private long generateRandomMillis(long offset) {
        long current = System.currentTimeMillis();
        return generateRandomLong(current - offset, current);
    }

    private long generateRandomLong(long min, long max) {
        return random.longs(min, max).findFirst().getAsLong();
    }

    private int generateRandomInt(int min, int max) {
        return random.ints(min, max).findFirst().getAsInt();
    }

    private double generateRandomDouble(double min, double max) {
        return random.doubles(min, max).findFirst().getAsDouble();
    }

}
