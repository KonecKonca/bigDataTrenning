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
    public  String testSchemaName;
    @Value("${data.schema.test.id}")
    public  String id;

    /* test-train constants*/
    @Value("${data.schema.test.dateTime}")
    public  String dateTime;
    @Value("${data.schema.test.siteName}")
    public  String siteName;
    @Value("${data.schema.test.posaContinent}")
    public  String posaContinent;
    @Value("${data.schema.test.userLocationCountry}")
    public  String userLocationCountry;
    @Value("${data.schema.test.userLocationRegion}")
    public  String userLocationRegion;
    @Value("${data.schema.test.userLocationCity}")
    public  String userLocationCity;
    @Value("${data.schema.test.origDestinationDistance}")
    public  String origDestinationDistance;
    @Value("${data.schema.test.userId}")
    public  String userId;
    @Value("${data.schema.test.isMobile}")
    public  String isMobile;
    @Value("${data.schema.test.isPackage}")
    public  String isPackage;
    @Value("${data.schema.test.channel}")
    public  String channel;
    @Value("${data.schema.test.srchCi}")
    public  String srchCi;
    @Value("${data.schema.test.srchCo}")
    public  String srchCo;
    @Value("${data.schema.test.srchAdultsCnt}")
    public  String srchAdultsCnt;
    @Value("${data.schema.test.srchChildrenCnt}")
    public  String srchChildrenCnt;
    @Value("${data.schema.test.srchRmCnt}")
    public  String srchRmCnt;
    @Value("${data.schema.test.srchDestinationId}")
    public  String srchDestinationId;
    @Value("${data.schema.test.srchDestinationTypeId}")
    public  String srchDestinationTypeId;
    @Value("${data.schema.test.hotelContinent}")
    public  String hotelContinent;
    @Value("${data.schema.test.hotelCountry}")
    public  String hotelCountry;
    @Value("${data.schema.test.hotelMarket}")
    public  String hotelMarket;

    /* train constants*/
    @Value("${data.schema.train.schemaName}")
    public  String trainSchemaName;
    @Value("${data.schema.train.hotelCluster}")
    public  String hotelCluster;
    @Value("${data.schema.train.isBooking}")
    public  String isBooking;
    @Value("${data.schema.train.cnt}")
    public  String cnt;

    /* SampleSumbission constants*/
    @Value("${data.schema.sampleSumbission.schemaName}")
    public  String sampleSumbissionSchemaName;
    @Value("${data.schema.sampleSumbission.id}")
    public  String sampleSumbissionId;
    @Value("${data.schema.sampleSumbission.hotelCluster}")
    public  String sampleSumbissionHotelCluster;

    /* destination constants*/
    @Value("${data.schema.destination.schemaName}")
    public  String destinationSchemaName;
    @Value("${data.schema.destination.d}")
    public  String destinationD;
    @Value("${data.schema.destination.srchDestinationId}")
    public  String destinationSrchDestinationId;

}
