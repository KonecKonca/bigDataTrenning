package kozitski.data.converter.scheme.impl;

import kozitski.data.converter.scheme.SchemaConstant;
import kozitski.data.converter.scheme.SchemaGenerator;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* generation Schema for Destination file */
@Component
public class DestinationSchemaGenerator implements SchemaGenerator {
    private static final Integer COUNT_OF_D_FIELD = 149;

    private SchemaConstant schemaConstant;

    @Autowired
    public void setSchemaConstant(SchemaConstant schemaConstant) {
        this.schemaConstant = schemaConstant;
    }

    @Override
    public Schema generateSchema() {

        SchemaBuilder.FieldAssembler<Schema> schemaFieldAssembler = SchemaBuilder.record(schemaConstant.destinationSchemaName)
                .fields()
                .optionalInt(schemaConstant.srchDestinationId);

        for (int i = NumberUtils.INTEGER_ONE; i <= COUNT_OF_D_FIELD; i++){
            schemaFieldAssembler.optionalDouble(schemaConstant.destinationD + i);
        }

        return schemaFieldAssembler.endRecord();

    }

}
