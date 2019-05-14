package kozitski.data.converter.io.writer.impl;

import kozitski.data.converter.dto.TestDTO;
import kozitski.data.converter.io.reader.AbstractCsvReader;
import kozitski.data.converter.io.reader.impl.TestCsvReader;
import kozitski.data.converter.io.writer.AbstractAvroWriter;
import kozitski.data.converter.scheme.SchemaConstant;
import kozitski.data.converter.scheme.impl.TestSchemaGenerator;
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
    private SchemaConstant schemaConstant;

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

    @Autowired
    public void setSchemaConstant(SchemaConstant schemaConstant) {
        this.schemaConstant = schemaConstant;
    }

    @Override
    public void init() {
        csvReader.init();
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
        record.put(schemaConstant.id, element.getId().orElse(null));
        record.put(schemaConstant.dateTime, element.getDateTime().orElse(null));
        record.put(schemaConstant.siteName, element.getSiteName().orElse(null));
        record.put(schemaConstant.posaContinent, element.getPosaContinent().orElse(null));
        record.put(schemaConstant.userLocationCountry, element.getUserLocationCountry().orElse(null));
        record.put(schemaConstant.userLocationRegion, element.getUserLocationRegion().orElse(null));
        record.put(schemaConstant.userLocationCity, element.getUserLocationCity().orElse(null));
        record.put(schemaConstant.origDestinationDistance, element.getOrigDestinationDistance().orElse(null));
        record.put(schemaConstant.userId, element.getUserId().orElse(null));
        record.put(schemaConstant.isMobile, element.getIsMobile().orElse(null));
        record.put(schemaConstant.isPackage, element.getIsPackage().orElse(null));
        record.put(schemaConstant.channel, element.getChannel().orElse(null));
        record.put(schemaConstant.srchCi, element.getSrchCi().orElse(null));
        record.put(schemaConstant.srchCo, element.getSrchCo().orElse(null));
        record.put(schemaConstant.srchAdultsCnt, element.getSrchAdultsCnt().orElse(null));
        record.put(schemaConstant.srchChildrenCnt, element.getSrchChildrenCnt().orElse(null));
        record.put(schemaConstant.srchRmCnt, element.getSrchRmCnt().orElse(null));
        record.put(schemaConstant.srchDestinationId, element.getSrchDestinationId().orElse(null));
        record.put(schemaConstant.srchDestinationTypeId, element.getSrchDestinationTypeId().orElse(null));
        record.put(schemaConstant.hotelContinent, element.getHotelContinent().orElse(null));
        record.put(schemaConstant.hotelCountry, element.getHotelCountry().orElse(null));
        record.put(schemaConstant.hotelMarket, element.getHotelMarket().orElse(null));
    }

}
