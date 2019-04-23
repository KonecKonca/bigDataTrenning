package kozitski.data.task2.domain.analyse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalisedStreet {
    private long streetId;
    private String streetName;
    private int crimeCount;
}
