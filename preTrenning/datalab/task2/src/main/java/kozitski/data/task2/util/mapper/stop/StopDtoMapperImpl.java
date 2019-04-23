package kozitski.data.task2.util.mapper.stop;

import kozitski.data.task2.domain.Location;
import kozitski.data.task2.domain.OutcomeObject;
import kozitski.data.task2.domain.Stop;
import kozitski.data.task2.domain.Street;
import kozitski.data.task2.domain.dto.StopDto;
import kozitski.data.task2.util.input.DayTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StopDtoMapperImpl implements StopDtoMapper {

    private DayTransformer stopDateTransformer;

    @Autowired
    public void setStopDateTransformer(DayTransformer stopDateTransformer) {
        this.stopDateTransformer = stopDateTransformer;
    }

    @Override
    public Stop map(StopDto dtoType) {
        return Stop
                .builder()
                    .ageRange(dtoType.getAgeRange())
                    .outcome(dtoType.getOutcome())
                    .involvedPerson(Boolean.parseBoolean(dtoType.getInvolvedPerson()))
                    .selfDefinedEthnicity(dtoType.getSelfDefinedEthnicity())
                    .gender(dtoType.getGender())
                    .legislation(dtoType.getLegislation())
                    .outcomeLinkedToObjectOfSearch(Boolean.parseBoolean(dtoType.getOutcomeLinkedToObjectOfSearch()))
                    .datetime(stopDateTransformer.toStringConvert(dtoType.getDatetime()))
                    .removalOfMoreThanOuterClothing(Boolean.parseBoolean(dtoType.getRemovalOfMoreThanOuterClothing()))
                    .outcomeObject(
                        OutcomeObject
                        .builder()
                            .id(dtoType.getOutcomeObject().getId())
                            .name(dtoType.getOutcomeObject().getName())
                        .build()
                    )
                    .location(
                        Location
                        .builder()
                            .latitude(Double.parseDouble(dtoType.getLocation().getLatitude()))
                            .longitude(Double.parseDouble(dtoType.getLocation().getLongitude()))
                            .street(Street
                                .builder()
                                .id(dtoType.getLocation().getStreet().getId())
                                .name(dtoType.getLocation().getStreet().getName())
                                .build()
                            )
                        .build()
                    )
                .operation(dtoType.getOperation())
                .officerDefinedEthnicity(dtoType.getOfficerDefinedEthnicity())
                .type(dtoType.getType())
                .operationName(dtoType.getOperationName())
                .objectOfSearch(dtoType.getObjectOfSearch())
                .build();
    }

}
