package kozitski.data.converter.scheme.impl;

import kozitski.data.converter.scheme.SchemaConstant;
import kozitski.data.converter.scheme.SchemaGenerator;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* generation Schema for SampleSubmission file */
@Component
public class SampleSubmissionSchemaGenerator implements SchemaGenerator {

    private SchemaConstant schemaConstant;

    @Autowired
    public void setSchemaConstant(SchemaConstant schemaConstant) {
        this.schemaConstant = schemaConstant;
    }

    @Override
    public Schema generateSchema() {
        return SchemaBuilder
            .record(schemaConstant.sampleSumbissionSchemaName)
                .fields()
                    .optionalInt(schemaConstant.sampleSumbissionId)
                    .optionalString(schemaConstant.sampleSumbissionHotelCluster)
            .endRecord();
    }

}
