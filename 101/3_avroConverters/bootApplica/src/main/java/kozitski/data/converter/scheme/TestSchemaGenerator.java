package kozitski.data.converter.scheme;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestSchemaGenerator implements SchemaGenerator {

    @Value("${data.schema.test.schemaName}")
    private String schemaName;
    @Value("${data.schema.test.id}")
    private String id;
    @Value("${data.schema.test.dateTime}")
    private String dateTime;
    @Value("${data.schema.test.siteName}")
    private String siteName;
    @Value("${data.schema.test.posaContinent}")
    private String posaContinent;
    @Value("${data.schema.test.userLocationCountry}")
    private String userLocationCountry;
    @Value("${data.schema.test.userLocationRegion}")
    private String userLocationRegion;
    @Value("${data.schema.test.userLocationCity}")
    private String userLocationCity;
    @Value("${data.schema.test.origDestinationDistance}")
    private String origDestinationDistance;
    @Value("${data.schema.test.userId}")
    private String userId;
    @Value("${data.schema.test.isMobile}")
    private String isMobile;
    @Value("${data.schema.test.isPackage}")
    private String isPackage;
    @Value("${data.schema.test.channel}")
    private String channel;
    @Value("${data.schema.test.srchCi}")
    private String srchCi;
    @Value("${data.schema.test.srchCo}")
    private String srchCo;
    @Value("${data.schema.test.srchAdultsCnt}")
    private String srchAdultsCnt;
    @Value("${data.schema.test.srchChildrenCnt}")
    private String srchChildrenCnt;
    @Value("${data.schema.test.srchRmCnt}")
    private String srchRmCnt;
    @Value("${data.schema.test.srchDestinationId}")
    private String srchDestinationId;
    @Value("${data.schema.test.srchDestinationTypeId}")
    private String srchDestinationTypeId;
    @Value("${data.schema.test.hotelContinent}")
    private String hotelContinent;
    @Value("${data.schema.test.hotelCountry}")
    private String hotelCountry;
    @Value("${data.schema.test.hotelMarket}")
    private String hotelMarket;

    @Override
    public Schema generateSchema() {
        return SchemaBuilder
                .record(schemaName)
                .fields()
                    .optionalString(id)
                    .optionalString(dateTime)
                    .optionalInt(siteName)
                    .optionalInt(posaContinent)
                    .optionalInt(userLocationCountry)
                    .optionalInt(userLocationRegion)
                    .optionalInt(userLocationCity)
                    .optionalDouble(origDestinationDistance)
                    .optionalInt(userId)
                    .optionalInt(isMobile)
                    .optionalInt(isPackage)
                    .optionalInt(channel)
                    .optionalString(srchCi)
                    .optionalString(srchCo)
                    .optionalInt(srchAdultsCnt)
                    .optionalInt(srchChildrenCnt)
                    .optionalString(srchRmCnt)
                    .optionalInt(srchDestinationId)
                    .optionalInt(srchDestinationTypeId)
                    .optionalInt(hotelContinent)
                    .optionalInt(hotelCountry)
                    .optionalInt(hotelMarket)
                .endRecord();
    }

}
