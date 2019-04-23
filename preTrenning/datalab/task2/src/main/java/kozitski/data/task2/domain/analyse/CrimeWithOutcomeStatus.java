package kozitski.data.task2.domain.analyse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrimeWithOutcomeStatus {
    private long streetId;
    private String streetName;
    private String outcomeCategory;
    private long crimesCount;
    private double percentage;
}
