package kozitski.data.task2.util.input;

import kozitski.data.task2.domain.dto.input.OutcomeWithDate;
import kozitski.data.task2.exeception.DateParseException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

@Component
public class OutcomeTransformer extends CommonDateTransformer {
    public static final String DATE_FORMAT_PATTERN = "yyyy-mm";

    public OutcomeWithDate toDateRangeDefaultFormat(String stringDate) {
        return toDateRange(stringDate, DATE_FORMAT_PATTERN);
    }

    public OutcomeWithDate toDateRange(String stringDate, String datePattern) {
        OutcomeWithDate outcomeWithDate = new OutcomeWithDate();

        try {
            String[] strings = stringDate.split(SPLIT_PATTERN);
            long fromDate = longConvert(strings[NumberUtils.BYTE_ZERO], datePattern);
            long toDate = longConvert(strings[NumberUtils.BYTE_ONE], datePattern);
            String outcome = strings[NumberUtils.INTEGER_TWO];

            outcomeWithDate.setFromDate(fromDate);
            outcomeWithDate.setToDate(toDate);
            outcomeWithDate.setOutcome(outcome);

        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DateParseException("Were entered incorrect date values", e);
        }

        return outcomeWithDate;
    }

    public long longConvert(String stringDate) {
        return longConvert(stringDate, DATE_FORMAT_PATTERN);
    }

}
