package kozitski.data.converter.scheme.impl;

import kozitski.data.converter.scheme.SchemaConstant;
import kozitski.data.converter.scheme.SchemaGenerator;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.springframework.stereotype.Component;

/* generation Schema for train file */
@Component
public class TrainSchemaGenerator implements SchemaGenerator {

    @Override
    public Schema generateSchema() {
        return SchemaBuilder
                .record(SchemaConstant.trainSchemaName)
                .fields()
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
                    .optionalInt(SchemaConstant.isBooking)
                    .optionalLong(SchemaConstant.cnt)
                    .optionalInt(SchemaConstant.hotelContinent)
                    .optionalInt(SchemaConstant.hotelCountry)
                    .optionalInt(SchemaConstant.hotelMarket)
                    .optionalInt(SchemaConstant.hotelCluster)
                .endRecord();
    }

}
