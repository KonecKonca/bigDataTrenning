package com.epam.kozitski.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrimeDto  implements Serializable {
    private String category;
    @JsonProperty("location_type")
    private String locationType;
    private Location location;
    private String context;
    @JsonProperty("outcome_status")
    private OutcomeStatus outcomeStatus;
    @JsonProperty("persistent_id")
    private String persistentId;
    private long id;
    @JsonProperty("location_subtype")
    private String locationSubtype;
    private String month;

    public OutcomeStatus getOutcomeStatus() {
        if(outcomeStatus == null){
            return new OutcomeStatus();
        }
        else return outcomeStatus;
    }
    public Location getLocation() {
        if(location == null){
            return new Location();
        }
        else return location;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OutcomeStatus implements Serializable {
        private String category;
        private String date;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Location {
        private String latitude;
        private Street street;
        private String longitude;

        public Street getStreet() {
            if(street == null){
                return new Street();
            }
            else {
                return street;
            }
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Street {
            private long id;
            private String name;
        }
    }

}
