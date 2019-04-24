package kozitski.data.converter.scheme.impl;

import kozitski.data.converter.scheme.SchemaConstant;
import kozitski.data.converter.scheme.SchemaGenerator;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* generation Schema for train file */
@Component
public class TrainSchemaGenerator implements SchemaGenerator {

    private SchemaConstant schemaConstant;

    @Autowired
    public void setSchemaConstant(SchemaConstant schemaConstant) {
        this.schemaConstant = schemaConstant;
    }

    @Override
    public Schema generateSchema() {
        return SchemaBuilder
                .record(schemaConstant.trainSchemaName)
                .fields()
                    .optionalString(schemaConstant.dateTime)
                    .optionalInt(schemaConstant.siteName)
                    .optionalInt(schemaConstant.posaContinent)
                    .optionalInt(schemaConstant.userLocationCountry)
                    .optionalInt(schemaConstant.userLocationRegion)
                    .optionalInt(schemaConstant.userLocationCity)
                    .optionalDouble(schemaConstant.origDestinationDistance)
                    .optionalInt(schemaConstant.userId)
                    .optionalInt(schemaConstant.isMobile)
                    .optionalInt(schemaConstant.isPackage)
                    .optionalInt(schemaConstant.channel)
                    .optionalString(schemaConstant.srchCi)
                    .optionalString(schemaConstant.srchCo)
                    .optionalInt(schemaConstant.srchAdultsCnt)
                    .optionalInt(schemaConstant.srchChildrenCnt)
                    .optionalString(schemaConstant.srchRmCnt)
                    .optionalInt(schemaConstant.srchDestinationId)
                    .optionalInt(schemaConstant.srchDestinationTypeId)
                    .optionalInt(schemaConstant.isBooking)
                    .optionalLong(schemaConstant.cnt)
                    .optionalInt(schemaConstant.hotelContinent)
                    .optionalInt(schemaConstant.hotelCountry)
                    .optionalInt(schemaConstant.hotelMarket)
                    .optionalInt(schemaConstant.hotelCluster)
                .endRecord();
    }

}
