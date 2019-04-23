package kozitski.data.task2.util.input;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommonDateTransformer {
    protected String SPLIT_PATTERN = "->";

    public long longConvert(String stringDate, String datePattern){
        long result;

        if(stringDate != null && !stringDate.isEmpty()){
            try {
                DateFormat format = new SimpleDateFormat(datePattern, Locale.ENGLISH);
                Date date = format.parse(stringDate);
                result = date.getTime();
            }
            catch (ParseException e){
                result = Integer.MIN_VALUE;
            }
        }
        else {
            result = Integer.MIN_VALUE;
        }

        return result;
    }

}
