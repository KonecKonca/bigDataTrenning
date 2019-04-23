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
public class StopDto implements Serializable {
    @JsonProperty("age_range")
    private String ageRange;
    private String outcome;
    @JsonProperty("involved_person")
    private String involvedPerson;
    @JsonProperty("self_defined_ethnicity")
    private String selfDefinedEthnicity;
    private String gender;
    private String legislation;
    @JsonProperty("outcome_linked_to_object_of_search")
    private String outcomeLinkedToObjectOfSearch;
    private String datetime;
    @JsonProperty("removal_of_more_than_outer_clothing")
    private String removalOfMoreThanOuterClothing;
    private String operation;
    @JsonProperty("officer_defined_ethnicity")
    private String officerDefinedEthnicity;
    private String type;
    @JsonProperty("operation_name")
    private String operationName;
    @JsonProperty("object_of_search")
    private String objectOfSearch;

    @JsonProperty("outcome_object")
    private OutcomeObject outcomeObject;
    @JsonProperty("location")
    private LocationDto location;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OutcomeObject implements Serializable{
        private String id;
        private String name;
    }


}
