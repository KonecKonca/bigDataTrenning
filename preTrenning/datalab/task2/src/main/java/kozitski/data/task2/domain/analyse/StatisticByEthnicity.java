package kozitski.data.task2.domain.analyse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticByEthnicity {
    private String officerDefinedEthnicity;
    private String month;
    private Long totalNumberOccurrences;
    private Long rateWithOutcomeArrest;
    private Long rateWithoutFurtherActionDisposal;
    private Long rateOtherOutcomes;
    private String popularObjectOfSearch;
}
