package by.epam.producer;

import org.apache.commons.cli.*;

import java.util.Properties;

public class Main {

    private static Options options = new Options();

    private static final int DEFAULT_NUM_OF_THREADS = 1;
    private static final int DEFAULT_DELAY = 100;

    public static void main(String[] args) {

        initOptions();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        String topic = cmd.getOptionValue("topic");
        String numberOfThreadsStr = cmd.getOptionValue("threads");
        String delayStr = cmd.getOptionValue("delay");

        int numberOfThreads;
        if (numberOfThreadsStr == null) {
            numberOfThreads = DEFAULT_NUM_OF_THREADS;
        } else {
            numberOfThreads = Integer.parseInt(numberOfThreadsStr);
            if (numberOfThreads < 1) {
                throw new IllegalArgumentException("The number of threads must be positive");
            }
        }

        int delay;
        if (delayStr == null) {
            delay = DEFAULT_DELAY;
        } else {
            delay = Integer.parseInt(delayStr);
            if (delay < 1) {
                throw new IllegalArgumentException("Delay must be positive");
            }
        }

        Properties props = new Properties();
        props.put("bootstrap.servers", "sandbox-hdp.hortonworks.com:6667");
        props.put("acks", "all");
        props.put("delivery.timeout.ms", 30000);
        props.put("batch.size", 16384);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "by.epam.producer.util.BookingEventSerializer");


        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new ProducerThread(topic, props, delay)).start();
        }

    }

    private static void initOptions() {

        Option topic = new Option("t", "topic", true, "kafka topic name");
        topic.setRequired(true);
        options.addOption(topic);

        Option numberOfThreads = new Option("threads", "threads", true, "the number of threads(1 by default)");
        numberOfThreads.setRequired(false);
        options.addOption(numberOfThreads);

        Option delay = new Option("d", "delay", true, "time in millis to configure delay between events in each thread(100 by default)");
        delay.setRequired(false);
        options.addOption(delay);

    }

}
