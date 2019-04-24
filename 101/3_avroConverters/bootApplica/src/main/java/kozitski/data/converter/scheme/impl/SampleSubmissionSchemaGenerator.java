package kozitski.data.converter.scheme.impl;

import kozitski.data.converter.scheme.SchemaConstant;
import kozitski.data.converter.scheme.SchemaGenerator;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.springframework.stereotype.Component;

/* generation Schema for SampleSubmission file */
@Component
public class SampleSubmissionSchemaGenerator implements SchemaGenerator {

    @Override
    public Schema generateSchema() {
        return SchemaBuilder
            .record(SchemaConstant.sampleSumbissionSchemaName)
                .fields()
                    .optionalInt(SchemaConstant.sampleSumbissionId)
                    .optionalString(SchemaConstant.sampleSumbissionHotelCluster)
            .endRecord();
    }

}
