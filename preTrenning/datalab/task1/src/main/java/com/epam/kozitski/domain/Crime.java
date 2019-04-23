package com.epam.kozitski.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crime{
    private String category;
    private String locationType;
    private Location location;
    private String context;
    private OutcomeStatus outcomeStatus;
    private String persistentId;
    private long id;
    private String locationSubtype;
    private long month;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OutcomeStatus {
        private String category;
        private long date;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Location {
        private double latitude;
        private Street street;
        private double longitude;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class Street {
            private long id;
            private String name;
        }
    }
}
