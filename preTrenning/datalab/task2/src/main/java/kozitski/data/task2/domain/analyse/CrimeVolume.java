package kozitski.data.task2.domain.analyse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrimeVolume {
    private String crimeCategory;
    private String month;
    private int currentMonthCount;
    private int previousMonthCount;
    private double deltaCount;
    private long growRate;
}
