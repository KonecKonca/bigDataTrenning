package kozitski.data.task2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crime{
    private String category;
    private String locationType;
    private Location location;
    private String context;
    private OutcomeStatus outcomeStatus;
    private String persistentId;
    private long id;
    private String locationSubtype;
    private long month;
}
