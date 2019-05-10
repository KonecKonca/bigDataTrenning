package com.kozitski.producer.util.generator;

import com.kozitski.producer.domain.BookingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * The type Booking event generator.
 */
@Component
public class BookingEventGenerator {
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private final SimpleDateFormat DATE_TIME_DATE_FORMAT = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
    private final SimpleDateFormat SRCH_CI_CO_DATE_FORMAT = new SimpleDateFormat(YYYY_MM_DD);

    private RandomGenerator randomGenerator;
    private BookingEventGeneratorParameter bookingParameter;

    /**
     * Sets random generator.
     *
     * @param randomGenerator the random generator
     */
    @Autowired
    public void setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    /**
     * Sets booking parameter.
     *
     * @param bookingParameter the booking parameter
     */
    @Autowired
    public void setBookingParameter(BookingEventGeneratorParameter bookingParameter) {
        this.bookingParameter = bookingParameter;
    }

    /**
     * Generate booking event.
     *
     * @return the booking event
     */
    public BookingEvent generate() {
        BookingEvent event = new BookingEvent();

        event.setDateTime(DATE_TIME_DATE_FORMAT.format(randomGenerator.generateRandomDate()));
        event.setSiteName(randomGenerator.generateRandomInt(bookingParameter.siteNameMin, bookingParameter.siteNameMax));
        event.setPosaContinent(randomGenerator.generateRandomInt(bookingParameter.posaContinentMin, bookingParameter.posaContinentMax));
        event.setUserLocationCountry(randomGenerator.generateRandomInt(bookingParameter.userLocationCountryMin, bookingParameter.userLocationCountryMax));
        event.setUserLocationRegion(randomGenerator.generateRandomInt(bookingParameter.userLocationRegionMin, bookingParameter.userLocationRegionMax));
        event.setUserLocationCity(randomGenerator.generateRandomInt(bookingParameter.userLocationCityMin, bookingParameter.userLocationCityMax));
        event.setOrigDestinationDistance(randomGenerator.generateRandomDouble(bookingParameter.origDestinationDistanceMin, bookingParameter.origDestinationDistanceMax));
        event.setUserId(randomGenerator.generateRandomInt(bookingParameter.userIdMin, bookingParameter.userIdMax));
        event.setIsMobile(randomGenerator.generateRandomInt(bookingParameter.isMobileMin, bookingParameter.isMobileMax));
        event.setIsPackage(randomGenerator.generateRandomInt(bookingParameter.isPackageMin, bookingParameter.isPackageMax));
        event.setChannel(randomGenerator.generateRandomInt(bookingParameter.channelMin, bookingParameter.channelMax));
        event.setSrchCi(SRCH_CI_CO_DATE_FORMAT.format(randomGenerator.generateRandomDate()));
        event.setSrchCo(SRCH_CI_CO_DATE_FORMAT.format(randomGenerator.generateRandomDate()));
        event.setSrchAdultsCnt(randomGenerator.generateRandomInt(bookingParameter.srchAdultsCntMin, bookingParameter.srchAdultsCntMax));
        event.setSrchChildrenCnt(randomGenerator.generateRandomInt(bookingParameter.srchChildrenCntMin, bookingParameter.srchChildrenCntMax));
        event.setSrchRmCnt(randomGenerator.generateRandomInt(bookingParameter.srchRmCntMin, bookingParameter.srchRmCntmax));
        event.setSrchDestinationId(randomGenerator.generateRandomInt(bookingParameter.srchDestinationIdMin, bookingParameter.srchDestinationIdMax));
        event.setSrchDestinationTypeId(randomGenerator.generateRandomInt(bookingParameter.srchDestinationTypeIdMin, bookingParameter.srchDestinationTypeIdMax));
        event.setHotelContinent(randomGenerator.generateRandomInt(bookingParameter.hotelContinentMin, bookingParameter.hotelContinentMax));
        event.setHotelCountry(randomGenerator.generateRandomInt(bookingParameter.hotelCountryMin, bookingParameter.hotelCountryMax));
        event.setHotelMarket(randomGenerator.generateRandomInt(bookingParameter.hotelMarketMin, bookingParameter.hotelMarketMax));
        event.setHotelCluster(randomGenerator.generateRandomInt(bookingParameter.hotelClusterMin, bookingParameter.hotelClusterMax));

        return event;
    }

}
