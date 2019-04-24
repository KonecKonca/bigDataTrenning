package kozitski.data.converter.io.writer.impl;

import kozitski.data.converter.dto.TestDTO;
import kozitski.data.converter.io.reader.AbstractCsvReader;
import kozitski.data.converter.io.reader.impl.TestCsvReader;
import kozitski.data.converter.io.writer.AbstractAvroWriter;
import kozitski.data.converter.scheme.SchemaConstant;
import kozitski.data.converter.scheme.impl.TestSchemaGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Test avro writer.
 */
@Component
public class TestAvroWriter extends AbstractAvroWriter<TestDTO> {

    private TestSchemaGenerator schemaGenerator;
    private TestCsvReader csvReader;

    /**
     * Sets schema generator.
     *
     * @param schemaGenerator the schema generator
     */
    @Autowired
    public void setSchemaGenerator(TestSchemaGenerator schemaGenerator) {
        this.schemaGenerator = schemaGenerator;
    }

    /**
     * Sets csv reader.
     *
     * @param csvReader the csv reader
     */
    @Autowired
    public void setCsvReader(TestCsvReader csvReader) {
        this.csvReader = csvReader;
    }

    @Override
    public Schema defineSchema() {
        return schemaGenerator.generateSchema();
    }

    @Override
    public AbstractCsvReader<TestDTO> defineCsvReader() {
        return csvReader;
    }

    @Override
    public void writeRecord(GenericData.Record record, TestDTO element) {
        record.put(SchemaConstant.id, element.getId().orElse(null));
        record.put(SchemaConstant.dateTime, element.getDateTime().orElse(null));
        record.put(SchemaConstant.siteName, element.getSiteName().orElse(null));
        record.put(SchemaConstant.posaContinent, element.getPosaContinent().orElse(null));
        record.put(SchemaConstant.userLocationCountry, element.getUserLocationCountry().orElse(null));
        record.put(SchemaConstant.userLocationRegion, element.getUserLocationRegion().orElse(null));
        record.put(SchemaConstant.userLocationCity, element.getUserLocationCity().orElse(null));
        record.put(SchemaConstant.origDestinationDistance, element.getOrigDestinationDistance().orElse(null));
        record.put(SchemaConstant.userId, element.getUserId().orElse(null));
        record.put(SchemaConstant.isMobile, element.getIsMobile().orElse(null));
        record.put(SchemaConstant.isPackage, element.getIsPackage().orElse(null));
        record.put(SchemaConstant.channel, element.getChannel().orElse(null));
        record.put(SchemaConstant.srchCi, element.getSrchCi().orElse(null));
        record.put(SchemaConstant.srchCo, element.getSrchCo().orElse(null));
        record.put(SchemaConstant.srchAdultsCnt, element.getSrchAdultsCnt().orElse(null));
        record.put(SchemaConstant.srchChildrenCnt, element.getSrchChildrenCnt().orElse(null));
        record.put(SchemaConstant.srchRmCnt, element.getSrchRmCnt().orElse(null));
        record.put(SchemaConstant.srchDestinationId, element.getSrchDestinationId().orElse(null));
        record.put(SchemaConstant.srchDestinationTypeId, element.getSrchDestinationTypeId().orElse(null));
        record.put(SchemaConstant.hotelContinent, element.getHotelContinent().orElse(null));
        record.put(SchemaConstant.hotelCountry, element.getHotelCountry().orElse(null));
        record.put(SchemaConstant.hotelMarket, element.getHotelMarket().orElse(null));
    }

}
