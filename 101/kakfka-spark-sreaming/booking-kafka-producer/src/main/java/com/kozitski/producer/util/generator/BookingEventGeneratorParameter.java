package com.kozitski.producer.util.generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * The type Booking event generator parameter.
 */
@Component
@PropertySource("classpath:bookingParameters.properties")
public class BookingEventGeneratorParameter {
    /**
     * The Site name min.
     */
    @Value("${booking.SiteName.min}")
    public  int siteNameMin;
    /**
     * The Site name max.
     */
    @Value("${booking.SiteName.max}")
    public  int siteNameMax;
    /**
     * The Test schema name.
     */
    @Value("${booking.PosaContinent.min}")
    public  int testSchemaName;
    /**
     * The Posa continent min.
     */
    @Value("${booking.PosaContinent.min}")
    public  int posaContinentMin;
    /**
     * The Posa continent max.
     */
    @Value("${booking.PosaContinent.max}")
    public  int posaContinentMax;
    /**
     * The User location country min.
     */
    @Value("${booking.UserLocationCountry.min}")
    public  int userLocationCountryMin;
    /**
     * The User location country max.
     */
    @Value("${booking.UserLocationCountry.max}")
    public  int userLocationCountryMax;
    /**
     * The User location region min.
     */
    @Value("${booking.UserLocationRegion.min}")
    public  int userLocationRegionMin;
    /**
     * The User location region max.
     */
    @Value("${booking.UserLocationRegion.max}")
    public  int userLocationRegionMax;
    /**
     * The User location city min.
     */
    @Value("${booking.UserLocationCity.min}")
    public  int userLocationCityMin;
    /**
     * The User location city max.
     */
    @Value("${booking.UserLocationCity.max}")
    public  int userLocationCityMax;
    /**
     * The Orig destination distance min.
     */
    @Value("${booking.OrigDestinationDistance.min}")
    public  int origDestinationDistanceMin;
    /**
     * The Orig destination distance max.
     */
    @Value("${booking.OrigDestinationDistance.max}")
    public  int origDestinationDistanceMax;
    /**
     * The User id min.
     */
    @Value("${booking.UserId.min}")
    public  int userIdMin;
    /**
     * The User id max.
     */
    @Value("${booking.UserId.max}")
    public  int userIdMax;
    /**
     * The Is mobile min.
     */
    @Value("${booking.IsMobile.min}")
    public  int isMobileMin;
    /**
     * The Is mobile max.
     */
    @Value("${booking.IsMobile.max}")
    public  int isMobileMax;
    /**
     * The Is package min.
     */
    @Value("${booking.IsPackage.min}")
    public  int isPackageMin;
    /**
     * The Is package max.
     */
    @Value("${booking.IsPackage.max}")
    public  int isPackageMax;
    /**
     * The Channel min.
     */
    @Value("${booking.Channel.min}")
    public  int channelMin;
    /**
     * The Channel max.
     */
    @Value("${booking.Channel.max}")
    public  int channelMax;
    /**
     * The Srch adults cnt min.
     */
    @Value("${booking.SrchAdultsCnt.min}")
    public  int srchAdultsCntMin;
    /**
     * The Srch adults cnt max.
     */
    @Value("${booking.SrchAdultsCnt.max}")
    public  int srchAdultsCntMax;
    /**
     * The Srch children cnt min.
     */
    @Value("${booking.SrchChildrenCnt.min}")
    public  int srchChildrenCntMin;
    /**
     * The Srch children cnt max.
     */
    @Value("${booking.SrchChildrenCnt.max}")
    public  int srchChildrenCntMax;
    /**
     * The Srch rm cnt min.
     */
    @Value("${booking.SrchRmCnt.min}")
    public  int srchRmCntMin;
    /**
     * The Srch rm cntmax.
     */
    @Value("${booking.SrchRmCnt.max}")
    public  int srchRmCntmax;
    /**
     * The Srch destination id min.
     */
    @Value("${booking.SrchDestinationId.min}")
    public  int srchDestinationIdMin;
    /**
     * The Srch destination id max.
     */
    @Value("${booking.SrchDestinationId.max}")
    public  int srchDestinationIdMax;
    /**
     * The Srch destination type id min.
     */
    @Value("${booking.SrchDestinationTypeId.min}")
    public  int srchDestinationTypeIdMin;
    /**
     * The Srch destination type id max.
     */
    @Value("${booking.SrchDestinationTypeId.max}")
    public  int srchDestinationTypeIdMax;
    /**
     * The Hotel continent min.
     */
    @Value("${booking.HotelContinent.min}")
    public  int hotelContinentMin;
    /**
     * The Hotel continent max.
     */
    @Value("${booking.HotelContinent.max}")
    public  int hotelContinentMax;
    /**
     * The Hotel country min.
     */
    @Value("${booking.HotelCountry.min}")
    public  int hotelCountryMin;
    /**
     * The Hotel country max.
     */
    @Value("${booking.HotelCountry.max}")
    public  int hotelCountryMax;
    /**
     * The Hotel market min.
     */
    @Value("${booking.HotelMarket.min}")
    public  int hotelMarketMin;
    /**
     * The Hotel market max.
     */
    @Value("${booking.HotelMarket.max}")
    public  int hotelMarketMax;
    /**
     * The Hotel cluster min.
     */
    @Value("${booking.HotelCluster.min}")
    public  int hotelClusterMin;
    /**
     * The Hotel cluster max.
     */
    @Value("${booking.HotelCluster.max}")
    public  int hotelClusterMax;
}
