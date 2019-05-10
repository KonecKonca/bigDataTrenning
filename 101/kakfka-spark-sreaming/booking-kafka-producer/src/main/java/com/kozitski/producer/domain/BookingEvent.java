package com.kozitski.producer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Booking event.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingEvent {
    /**
     * The constant COMMA.
     */
    public static final String COMMA = ", ";

    private String dateTime;
    private int siteName;
    private int posaContinent;
    private int userLocationCountry;
    private int userLocationRegion;
    private int userLocationCity;
    private double origDestinationDistance;
    private int userId;
    private int isMobile;
    private int isPackage;
    private int channel;
    private String srchCi;
    private String srchCo;
    private int srchAdultsCnt;
    private int srchChildrenCnt;
    private int srchRmCnt;
    private int srchDestinationId;
    private int srchDestinationTypeId;
    private int isBooking;
    private long cnt;
    private int hotelContinent;
    private int hotelCountry;
    private int hotelMarket;
    private int hotelCluster;
}
