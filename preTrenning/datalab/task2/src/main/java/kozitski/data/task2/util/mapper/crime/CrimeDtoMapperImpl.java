package kozitski.data.task2.util.mapper.crime;

import kozitski.data.task2.domain.Crime;
import kozitski.data.task2.domain.Location;
import kozitski.data.task2.domain.OutcomeStatus;
import kozitski.data.task2.domain.Street;
import kozitski.data.task2.domain.dto.CrimeDto;
import kozitski.data.task2.util.input.MonthTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrimeDtoMapperImpl implements CrimeDtoMapper {

    private MonthTransformer crimeMonthTransformer;

    @Autowired
    public void setCrimeMonthTransformer(MonthTransformer crimeMonthTransformer) {
        this.crimeMonthTransformer = crimeMonthTransformer;
    }

    @Override
    public Crime map(CrimeDto crimeDto) {
        return  Crime
                .builder()
                    .category(crimeDto.getCategory())
                    .locationType(crimeDto.getLocationType())
                    .location(
                        Location
                        .builder()
                            .latitude(Double.parseDouble(crimeDto.getLocation().getLatitude()))
                            .longitude(Double.parseDouble(crimeDto.getLocation().getLongitude()))
                            .street(
                                Street
                                .builder()
                                    .id(crimeDto.getLocation().getStreet().getId())
                                    .name(crimeDto.getLocation().getStreet().getName())
                                .build()
                            )
                        .build()
                    )
                    .context(crimeDto.getContext())
                    .outcomeStatus(
                        OutcomeStatus
                        .builder()
                            .category(crimeDto.getCategory())
                            .date(crimeMonthTransformer.longConvert(crimeDto.getOutcomeStatus().getDate()))
                        .build()
                    )
                    .persistentId(crimeDto.getPersistentId())
                    .locationSubtype(crimeDto.getLocationSubtype())
                    .month(crimeMonthTransformer.longConvert(crimeDto.getMonth()))
                    .id(crimeDto.getId())
                .build();
    }

}
