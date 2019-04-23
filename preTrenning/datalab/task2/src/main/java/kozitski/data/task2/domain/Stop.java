package kozitski.data.task2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stop {
    private String ageRange;
    private String outcome;
    private boolean involvedPerson;
    private String selfDefinedEthnicity;
    private String gender;
    private String legislation;
    private boolean outcomeLinkedToObjectOfSearch;
    private String datetime;
    private boolean removalOfMoreThanOuterClothing;
    private String operation;
    private String officerDefinedEthnicity;
    private String type;
    private String operationName;
    private String objectOfSearch;
    private OutcomeObject outcomeObject;
    private Location location;
}
