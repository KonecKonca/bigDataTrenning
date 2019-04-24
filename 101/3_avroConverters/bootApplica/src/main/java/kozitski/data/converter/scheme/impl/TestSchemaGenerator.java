package kozitski.data.converter.scheme.impl;

import kozitski.data.converter.scheme.SchemaConstant;
import kozitski.data.converter.scheme.SchemaGenerator;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.springframework.stereotype.Component;

/* generation Schema for test file */
@Component
public class TestSchemaGenerator implements SchemaGenerator {

    @Override
    public Schema generateSchema() {
        return SchemaBuilder
                .record(SchemaConstant.testSchemaName)
                .fields()
                    .optionalString(SchemaConstant.id)
                    .optionalString(SchemaConstant.dateTime)
                    .optionalInt(SchemaConstant.siteName)
                    .optionalInt(SchemaConstant.posaContinent)
                    .optionalInt(SchemaConstant.userLocationCountry)
                    .optionalInt(SchemaConstant.userLocationRegion)
                    .optionalInt(SchemaConstant.userLocationCity)
                    .optionalDouble(SchemaConstant.origDestinationDistance)
                    .optionalInt(SchemaConstant.userId)
                    .optionalInt(SchemaConstant.isMobile)
                    .optionalInt(SchemaConstant.isPackage)
                    .optionalInt(SchemaConstant.channel)
                    .optionalString(SchemaConstant.srchCi)
                    .optionalString(SchemaConstant.srchCo)
                    .optionalInt(SchemaConstant.srchAdultsCnt)
                    .optionalInt(SchemaConstant.srchChildrenCnt)
                    .optionalString(SchemaConstant.srchRmCnt)
                    .optionalInt(SchemaConstant.srchDestinationId)
                    .optionalInt(SchemaConstant.srchDestinationTypeId)
                    .optionalInt(SchemaConstant.hotelContinent)
                    .optionalInt(SchemaConstant.hotelCountry)
                    .optionalInt(SchemaConstant.hotelMarket)
                .endRecord();
    }

}
