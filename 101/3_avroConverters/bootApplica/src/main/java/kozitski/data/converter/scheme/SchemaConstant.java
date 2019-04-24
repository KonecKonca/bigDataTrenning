package kozitski.data.converter.scheme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/* fields name constants for avro-shemes */
@Component
@PropertySource("classpath:schema.properties")
public class SchemaConstant {

    private SchemaConstant() { }

    /* test constants*/
    @Value("${data.schema.test.schemaName}")
    public static String testSchemaName;
    @Value("${data.schema.test.id}")
    public static String id;

    /* test-train constants*/
    @Value("${data.schema.test.dateTime}")
    public static String dateTime;
    @Value("${data.schema.test.siteName}")
    public static String siteName;
    @Value("${data.schema.test.posaContinent}")
    public static String posaContinent;
    @Value("${data.schema.test.userLocationCountry}")
    public static String userLocationCountry;
    @Value("${data.schema.test.userLocationRegion}")
    public static String userLocationRegion;
    @Value("${data.schema.test.userLocationCity}")
    public static String userLocationCity;
    @Value("${data.schema.test.origDestinationDistance}")
    public static String origDestinationDistance;
    @Value("${data.schema.test.userId}")
    public static String userId;
    @Value("${data.schema.test.isMobile}")
    public static String isMobile;
    @Value("${data.schema.test.isPackage}")
    public static String isPackage;
    @Value("${data.schema.test.channel}")
    public static String channel;
    @Value("${data.schema.test.srchCi}")
    public static String srchCi;
    @Value("${data.schema.test.srchCo}")
    public static String srchCo;
    @Value("${data.schema.test.srchAdultsCnt}")
    public static String srchAdultsCnt;
    @Value("${data.schema.test.srchChildrenCnt}")
    public static String srchChildrenCnt;
    @Value("${data.schema.test.srchRmCnt}")
    public static String srchRmCnt;
    @Value("${data.schema.test.srchDestinationId}")
    public static String srchDestinationId;
    @Value("${data.schema.test.srchDestinationTypeId}")
    public static String srchDestinationTypeId;
    @Value("${data.schema.test.hotelContinent}")
    public static String hotelContinent;
    @Value("${data.schema.test.hotelCountry}")
    public static String hotelCountry;
    @Value("${data.schema.test.hotelMarket}")
    public static String hotelMarket;

    /* train constants*/
    @Value("${data.schema.train.schemaName}")
    public static String trainSchemaName;
    @Value("${data.schema.train.hotelCluster}")
    public static String hotelCluster;
    @Value("${data.schema.train.isBooking}")
    public static String isBooking;
    @Value("${data.schema.train.cnt}")
    public static String cnt;

    /* SampleSumbission constants*/
    @Value("${data.schema.sampleSumbission.schemaName}")
    public static String sampleSumbissionSchemaName;
    @Value("${data.schema.sampleSumbission.id}")
    public static String sampleSumbissionId;
    @Value("${data.schema.sampleSumbission.hotelCluster}")
    public static String sampleSumbissionHotelCluster;

    /* destination constants*/
    @Value("${data.schema.destination.schemaName}")
    public static String destinationSchemaName;
    @Value("${data.schema.destination.d}")
    public static String destinationD;
    @Value("${data.schema.destination.srchDestinationId}")
    public static String destinationSrchDestinationId;

}
