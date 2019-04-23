package kozitski.data.task2.domain.analyse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SnapshotOfStreetLevel {
    private long streetId;
    private String streetName;
    private String mostPopularAgeRange;
    private String mostPopularGenderRange;
    private String mostPopularEthnicity;
    private String mostPopularObjectOfSearch;
    private String mostPopularOutcome;
}
