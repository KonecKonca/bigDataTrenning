package kozitski.data.task2.domain.dto;

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
    @JsonProperty("location")
    private LocationDto location;
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
    public LocationDto getLocation() {
        if(location == null){
            return new LocationDto();
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


}
