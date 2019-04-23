package kozitski.data.converter.io;

public class IOConstant {

    private IOConstant() {}

    public static final int PARTS_SIZE = 100_000;

    public static final String TEST_READ_PATH = "/user/maria_dev/data/test.csv";
    public static final String TEST_WRITE_PATH = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/avro_data/test.avro";

    public static final String TRAIN_READ_PATH = "/home/maria_dev/train.csv";
    public static final String TRAIN_WRITE_PATH = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/avro_data/train.avro";



}
