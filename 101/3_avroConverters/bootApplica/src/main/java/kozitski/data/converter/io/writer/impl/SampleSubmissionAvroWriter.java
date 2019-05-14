package kozitski.data.converter.io.writer.impl;

import kozitski.data.converter.dto.SampleSubmissionDTO;
import kozitski.data.converter.io.reader.AbstractCsvReader;
import kozitski.data.converter.io.reader.impl.SampleSubmissionCsvReader;
import kozitski.data.converter.io.writer.AbstractAvroWriter;
import kozitski.data.converter.scheme.SchemaConstant;
import kozitski.data.converter.scheme.impl.SampleSubmissionSchemaGenerator;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Sample submission avro writer.
 */
@Component
public class SampleSubmissionAvroWriter extends AbstractAvroWriter<SampleSubmissionDTO> {

    private SampleSubmissionSchemaGenerator sampleSubmissionSchemaGenerator;
    private SampleSubmissionCsvReader sampleSubmissionCsvReader;
    private SchemaConstant schemaConstant;

    /**
     * Sets sample submission schema generator.
     *
     * @param sampleSubmissionSchemaGenerator the sample submission schema generator
     */
    @Autowired
    public void setSampleSubmissionSchemaGenerator(SampleSubmissionSchemaGenerator sampleSubmissionSchemaGenerator) {
        this.sampleSubmissionSchemaGenerator = sampleSubmissionSchemaGenerator;
    }

    /**
     * Sets sample submission csv reader.
     *
     * @param sampleSubmissionCsvReader the sample submission csv reader
     */
    @Autowired
    public void setSampleSubmissionCsvReader(SampleSubmissionCsvReader sampleSubmissionCsvReader) {
        this.sampleSubmissionCsvReader = sampleSubmissionCsvReader;
    }

    @Autowired
    public void setSchemaConstant(SchemaConstant schemaConstant) {
        this.schemaConstant = schemaConstant;
    }

    @Override
    public void init() {
        sampleSubmissionCsvReader.init();
    }

    @Override
    public Schema defineSchema() {
        return sampleSubmissionSchemaGenerator.generateSchema();
    }

    @Override
    public AbstractCsvReader<SampleSubmissionDTO> defineCsvReader() {
        return sampleSubmissionCsvReader;
    }

    @Override
    public void writeRecord(GenericData.Record record, SampleSubmissionDTO element) {
        record.put(schemaConstant.sampleSumbissionId, element.getId().orElse(null));
        record.put(schemaConstant.hotelCluster, element.getHotelCluster().orElse(null));
    }

}
