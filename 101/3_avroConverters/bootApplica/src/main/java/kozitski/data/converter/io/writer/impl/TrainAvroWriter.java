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
        record.put(SchemaConstant.isBooking, element.getIsBooking().orElse(null));
        record.put(SchemaConstant.cnt, element.getCnt().orElse(null));
        record.put(SchemaConstant.hotelContinent, element.getHotelContinent().orElse(null));
        record.put(SchemaConstant.hotelCountry, element.getHotelCountry().orElse(null));
        record.put(SchemaConstant.hotelMarket, element.getHotelMarket().orElse(null));
        record.put(SchemaConstant.hotelCluster, element.getHotelCluster().orElse(null));
    }


}
