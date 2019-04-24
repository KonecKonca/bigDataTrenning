package kozitski.data.converter.io;

/**
 * The type Io constant.
 */
public class IOConstant {

    private IOConstant() {}

    /**
     * The constant PARTS_SIZE.
     */
    public static final int PARTS_SIZE = 100_000;

    /**
     * The constant TEST_READ_PATH.
     */
    public static final String TEST_READ_PATH = "/user/maria_dev/data/test.csv";
    /**
     * The constant TEST_WRITE_PATH.
     */
    public static final String TEST_WRITE_PATH = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/avro_data/test.avro";

    /**
     * The constant TRAIN_READ_PATH.
     */
    public static final String TRAIN_READ_PATH = "/home/maria_dev/train.csv";
    /**
     * The constant TRAIN_WRITE_PATH.
     */
    public static final String TRAIN_WRITE_PATH = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/avro_data/train.avro";

    /**
     * The constant DESTINATION_READ_PATH.
     */
    public static final String DESTINATION_READ_PATH = "/home/maria_dev/destination.csv";
    /**
     * The constant DESTINATION_WRITE_PATH.
     */
    public static final String DESTINATION_WRITE_PATH = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/avro_data/destination.avro";

    /**
     * The constant SAMPLE_SUBMITION_READ_PATH.
     */
    public static final String SAMPLE_SUBMITION_READ_PATH = "/home/maria_dev/sample_submission.csv";
    /**
     * The constant SAMPLE_SUBMITION_WRITE_PATH.
     */
    public static final String SAMPLE_SUBMITION_WRITE_PATH = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/avro_data/sample_submission.avro";

}
