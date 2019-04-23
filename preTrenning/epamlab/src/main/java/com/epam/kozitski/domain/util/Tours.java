package com.epam.kozitski.domain.util;

import com.epam.kozitski.domain.Tour;
import com.epam.kozitski.domain.TourType;

import java.util.Base64;

public class Tours {
    private static final long DEFAULT_TOUR_ID = 1;
    private static final Base64 DEFAULT_PHOTO = null;
    private static final long DEFAULT_DATE = System.currentTimeMillis();
    private static final int DEFAULT_DURATION = 100;
    private static final String DEFAULT_DESCRIPTION = "best tour in the word";
    private static final double DEFAULT_COST = 999;
    private static final TourType DEFAULT_TOUR_TYPE = TourType.SCLASS;
    private static final long DEFAULT_HOTEL_ID = 1;
    private static final long DEFAULT_COUNTRY_ID = 1;


    public static Tour createDefaultTour(){
        return Tour
                .builder()
                .id(DEFAULT_TOUR_ID)
                .photo(DEFAULT_PHOTO)
                .date(DEFAULT_DATE)
                .duration(DEFAULT_DURATION)
                .description(DEFAULT_DESCRIPTION)
                .cost(DEFAULT_COST)
                .tourType(DEFAULT_TOUR_TYPE)
                .hotel_id(DEFAULT_HOTEL_ID)
                .country_id(DEFAULT_COUNTRY_ID)
                .build();
    }

}
