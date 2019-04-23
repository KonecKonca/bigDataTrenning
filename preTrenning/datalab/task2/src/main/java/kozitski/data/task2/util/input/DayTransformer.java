package kozitski.data.task2.util.input;

import kozitski.data.task2.domain.dto.input.StringDataRange;
import kozitski.data.task2.exeception.DateParseException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;


@Component
public class DayTransformer extends CommonDateTransformer {
    public static final String DATE_FORMAT_PATTERN = "yyyy-mm-dd";
    public static final int END_INDEX = 10;

    public StringDataRange toDateRangeDefaultFormat(String stringDate) {
        StringDataRange dateRange = new StringDataRange();

        try {
            String[] strings = stringDate.split(SPLIT_PATTERN);
            dateRange.setFromDate(strings[NumberUtils.BYTE_ZERO]);
            dateRange.setToDate(strings[NumberUtils.BYTE_ONE]);
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DateParseException("Were entered incorrect date values", e);
        }

        return dateRange;
    }

    public String toStringConvert(String stringDate) {
        return stringDate.substring(NumberUtils.BYTE_ZERO, END_INDEX);
    }

    public long  longConvert(String stringDate){
        return longConvert(stringDate, DATE_FORMAT_PATTERN);
    }

}
