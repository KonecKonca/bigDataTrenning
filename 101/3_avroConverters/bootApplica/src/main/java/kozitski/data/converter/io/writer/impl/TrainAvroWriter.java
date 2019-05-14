package kozitski.data.converter.io.writer.impl;

import kozitski.data.converter.dto.TrainDTO;
import kozitski.data.converter.io.reader.AbstractCsvReader;
import kozitski.data.converter.io.reader.impl.TrainCsvReader;
import kozitski.data.converter.io.writer.AbstractAvroWriter;
import kozitski.data.converter.scheme.SchemaConstant;
import kozitski.data.converter.scheme.impl.TrainSchemaGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Train avro writer.
 */
@Component
@Slf4j
public class TrainAvroWriter extends AbstractAvroWriter<TrainDTO> {

    private TrainSchemaGenerator trainSchemaGenerator;
    private TrainCsvReader trainCsvReader;
    private SchemaConstant schemaConstant;

    /**
     * Sets train schema generator.
     *
     * @param trainSchemaGenerator the train schema generator
     */
    @Autowired
    public void setTrainSchemaGenerator(TrainSchemaGenerator trainSchemaGenerator) {
        this.trainSchemaGenerator = trainSchemaGenerator;
    }

    /**
     * Sets train csv reader.
     *
     * @param trainCsvReader the train csv reader
     */
    @Autowired
    public void setTrainCsvReader(TrainCsvReader trainCsvReader) {
        this.trainCsvReader = trainCsvReader;
    }

    @Autowired
    public void setSchemaConstant(SchemaConstant schemaConstant) {
        this.schemaConstant = schemaConstant;
    }

    @Override
    public void init() {
        trainCsvReader.init();
    }

    @Override
    public Schema defineSchema() {
        return trainSchemaGenerator.generateSchema();
    }

    @Override
    public AbstractCsvReader<TrainDTO> defineCsvReader() {
        return trainCsvReader;
    }

    @Override
    public void writeRecord(GenericData.Record record, TrainDTO element) {
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
        record.put(schemaConstant.isBooking, element.getIsBooking().orElse(null));
        record.put(schemaConstant.cnt, element.getCnt().orElse(null));
        record.put(schemaConstant.hotelContinent, element.getHotelContinent().orElse(null));
        record.put(schemaConstant.hotelCountry, element.getHotelCountry().orElse(null));
        record.put(schemaConstant.hotelMarket, element.getHotelMarket().orElse(null));
        record.put(schemaConstant.hotelCluster, element.getHotelCluster().orElse(null));
    }


}
