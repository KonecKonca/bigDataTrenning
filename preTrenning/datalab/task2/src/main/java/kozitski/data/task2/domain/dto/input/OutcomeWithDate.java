package kozitski.data.task2.domain.dto.input;

import lombok.Data;

@Data
public class OutcomeWithDate {
    private long fromDate;
    private long toDate;
    private String outcome;
}
