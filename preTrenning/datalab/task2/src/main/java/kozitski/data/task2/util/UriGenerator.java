package kozitski.data.task2.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@Slf4j
public class UriGenerator {
    private static final String PARAMETER_DELIMETER = "?";
    private static final String PARAMETER_INIT = "=";
    private static final String PARAMETER_UNION = "&";

    /* This arguments can be used only at this order */
    private static final String LAT_PARAMETER = "lat";
    private static final String LNG_PARAMETER = "lng";
    private static final String DATE_PARAMETER = "date";

    public URI generateUri(String commonUrl, String latitude, String longitude){
        return stringToUri(generateStringUri(commonUrl, latitude, longitude));
    }
    public URI generateUriByDate(String commonUrl, String latitude, String longitude, String date){
        return stringToUri(generateStringUriByDate(commonUrl, latitude, longitude, date));
    }


    public String generateStringUri(String commonUrl, String latitude, String longitude){
        StringBuilder stringUri = new StringBuilder(commonUrl);
        stringUri.append(PARAMETER_DELIMETER);

        stringUri.append(LAT_PARAMETER);
        stringUri.append(PARAMETER_INIT);
        stringUri.append(latitude);
        stringUri.append(PARAMETER_UNION);

        stringUri.append(LNG_PARAMETER);
        stringUri.append(PARAMETER_INIT);
        stringUri.append(longitude);

        return stringUri.toString();
    }
    public String generateStringUriByDate(String commonUrl, String latitude, String longitude, String date){
        StringBuilder stringUri = new StringBuilder(generateStringUri(commonUrl, latitude, longitude));

        stringUri.append(PARAMETER_UNION);

        stringUri.append(DATE_PARAMETER);
        stringUri.append(PARAMETER_INIT);
        stringUri.append(date);

        return stringUri.toString();
    }
    private URI stringToUri(String stringUri){
        URI uri = null;

        try {
            uri = new  URI(stringUri);
        }
        catch (URISyntaxException e) {
            log.warn("String URI is incorrect", e);
        }

        return uri;
    }

}
