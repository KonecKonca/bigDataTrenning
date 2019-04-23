package com.epam.kozitski.domain.util;

import com.epam.kozitski.domain.UserTour;

public class UserTours {
    private static final long DEFAULT_USER_ID = 1;
    private static final long DEFAULT_TOUR_ID = 1;

    public static UserTour createDefaultUserTour(){
        return new UserTour(DEFAULT_USER_ID, DEFAULT_TOUR_ID);
    }

}
