package com.epam.kozitski.domain.util;

import com.epam.kozitski.domain.Features;
import com.epam.kozitski.domain.Hotel;

import java.net.URL;

public class Hotels {
    private static final long DEFAULT_HOTEL_ID = 1;
    private static final String DEFAULT_HOTEL_NAME = "Planeta";
    private static final short DEFAULT_HOTEL_STARS = 5;
    private static final URL DEFAULT_HOTEL_WEBSITE = null;
    private static final double DEFAULT_HOTEL_LATITUDE = 134.8823234;
    private static final double DEFAULT_HOTEL_LONGITUDE = 58.29131765;
    private static final Features DEFAULT_HOTEL_FEATURE = Features.OTHER;

    public static Hotel createDefaultHotel(){
        return Hotel
                .builder()
                .id(DEFAULT_HOTEL_ID)
                .name(DEFAULT_HOTEL_NAME)
                .stars(DEFAULT_HOTEL_STARS)
                .website(DEFAULT_HOTEL_WEBSITE)
                .latitude(DEFAULT_HOTEL_LATITUDE)
                .longitude(DEFAULT_HOTEL_LONGITUDE)
                .features(DEFAULT_HOTEL_FEATURE)
                .build();
    }

}
