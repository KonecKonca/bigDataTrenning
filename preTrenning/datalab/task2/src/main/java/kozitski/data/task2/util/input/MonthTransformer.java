package kozitski.data.task2.util.input;

import kozitski.data.task2.domain.dto.input.DateRange;
import kozitski.data.task2.exeception.DateParseException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

@Component
public class MonthTransformer extends CommonDateTransformer {
    public static final String DATE_FORMAT_PATTERN = "yyyy-mm";

    public DateRange toDateRangeDefaultFormat(String stringDate) {
        return toDateRange(stringDate, DATE_FORMAT_PATTERN);
    }

    public DateRange toDateRange(String stringDate, String datePattern) {
        DateRange dateRange = new DateRange();

        try {
            String[] strings = stringDate.split(SPLIT_PATTERN);
            long fromDate = longConvert(strings[NumberUtils.BYTE_ZERO], datePattern);
            long toDate = longConvert(strings[NumberUtils.BYTE_ONE], datePattern);
            dateRange.setFromDate(fromDate);
            dateRange.setToDate(toDate);

        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DateParseException("Were entered incorrect date values", e);
        }

        return dateRange;
    }

    public long longConvert(String stringDate) {
        return longConvert(stringDate, DATE_FORMAT_PATTERN);
    }


}
