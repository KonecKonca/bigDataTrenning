package kozitski.data.converter.scheme;

import org.apache.avro.Schema;

/**
 * The interface Schema generator.
 */
public interface SchemaGenerator {

    /**
     * Generate schema schema.
     *
     * @return the schema
     */
    Schema generateSchema();

}
