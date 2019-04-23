package kozitski.data.task2.domain.analyse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StopAndSearchCorrelation {
    private Long streetId;
    private String streetName;
    private String month;
    private Long drugsCrimesCount;
    private Long drugsStopsCount;
    private Long theftCrimesCount;
    private Long theftStopAndSearchCount;
}
