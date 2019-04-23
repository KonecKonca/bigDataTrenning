package com.epam.kozitski.domain.util;

import com.epam.kozitski.domain.Review;

public class Reviews {
    private static final long DEFAULT_REVIEW_ID = 1;
    private static final long DEFAULT_DATE = System.currentTimeMillis();
    private static final String DEFAULT_TEXT = "Any text";
    private static final long DEFAULT_REVIEW_USER_ID = 1;
    private static final long DEFAULT_REVIEW_TOUR_ID = 1;

    public static Review createDefaultReview(){
        return Review
                .builder()
                .id(DEFAULT_REVIEW_ID)
                .date(DEFAULT_DATE)
                .text(DEFAULT_TEXT)
                .user_id(DEFAULT_REVIEW_USER_ID)
                .tour_id(DEFAULT_REVIEW_TOUR_ID)
                .build();
    }

}
