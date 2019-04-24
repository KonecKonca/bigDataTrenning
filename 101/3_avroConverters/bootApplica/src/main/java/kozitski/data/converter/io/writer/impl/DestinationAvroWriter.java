package kozitski.data.converter.io.writer.impl;

import kozitski.data.converter.dto.DestinationDTO;
import kozitski.data.converter.io.reader.AbstractCsvReader;
import kozitski.data.converter.io.reader.impl.DestinationCsvReader;
import kozitski.data.converter.io.writer.AbstractAvroWriter;
import kozitski.data.converter.scheme.SchemaConstant;
import kozitski.data.converter.scheme.impl.DestinationSchemaGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * The type Destination avro writer.
 */
@Component
public class DestinationAvroWriter extends AbstractAvroWriter<DestinationDTO> {

    private DestinationSchemaGenerator destinationSchemaGenerator;
    private DestinationCsvReader destinationCsvReader;

    @Override
    public Schema defineSchema() {
        return destinationSchemaGenerator.generateSchema();
    }

    @Override
    public AbstractCsvReader<DestinationDTO> defineCsvReader() {
        return destinationCsvReader;
    }

    @Override
    public void writeRecord(GenericData.Record record, DestinationDTO element) {

        record.put(SchemaConstant.srchDestinationId, element.getSrchDestinationId().orElse(null));
        List<Optional<Double>> list = element.getD();
        for (int i = NumberUtils.INTEGER_ZERO; i < list.size(); i++) {
            record.put(SchemaConstant.destinationD + (i + NumberUtils.INTEGER_ONE), list.get(i).orElse(null));
        }

    }

}
