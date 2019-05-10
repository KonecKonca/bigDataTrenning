package com.kozitski.producer.runner;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * The type Producer application runner.
 */
@Component
@Slf4j
public class ProducerApplicationRunner {
    private static final String T = "t";
    private static final String TOPIC = "topic";
    private static final String KAFKA_TOPIC_NAME = "kafka topic name";
    private static final String THREADS = "threads";
    private static final String THE_NUMBER_OF_THREADS_1_BY_DEFAULT = "the number of threads(1 by default)";
    private static final String D = "d";
    private static final String DELAY = "delay";
    private static final String TIME_PARAMETER_DESCRIPTION = "time in millis to configure delay between events in each thread(100 by default)";
    private static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
    private static final String DEFAULT_SERVER = "sandbox-hdp.hortonworks.com:6667";
    private static final String ACKS = "acks";
    private static final String ALL = "all";
    private static final String DELIVERY_TIMEOUT_MS = "delivery.timeout.ms";
    private static final int DEVAULT_TIMEOUT = 30000;
    private static final String BATCH_SIZE = "batch.size";
    private static final int DEFAULT_BATCH_SIZE = 16384;
    private static final String BUFFER_MEMORY = "buffer.memory";
    private static final int DEFAULT_BUFFER_MEMORY = 33554432;
    private static final String KEY_SERIALIZER = "key.serializer";
    private static final String KEY_KAFKA_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String VALUE_SERIALIZER = "com.kozitski.producer.util.BookingEventSerializer";
    private static Options options = new Options();

    private static final int DEFAULT_NUM_OF_THREADS = 1;
    private static final int DEFAULT_DELAY = 100;

    /**
     * Start.
     *
     * @param args the args
     */
    public void start(String[] args) {

        initOptions();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        }
        catch (ParseException e) {
            log.error("Incorrect argument", e);
            System.exit(NumberUtils.INTEGER_ZERO);
        }

        String topic = cmd.getOptionValue(TOPIC);
        String numberOfThreadsStr = cmd.getOptionValue(THREADS);
        String delayStr = cmd.getOptionValue(DELAY);

        int numberOfThreads;
        if (numberOfThreadsStr == null) {
            numberOfThreads = DEFAULT_NUM_OF_THREADS;
        } else {
            numberOfThreads = Integer.parseInt(numberOfThreadsStr);
            if (numberOfThreads < NumberUtils.INTEGER_ONE) {
                throw new IllegalArgumentException("The number of threads must be positive");
            }
        }

        int delay;
        if (delayStr == null) {
            delay = DEFAULT_DELAY;
        } else {
            delay = Integer.parseInt(delayStr);
            if (delay < NumberUtils.INTEGER_ONE) {
                throw new IllegalArgumentException("Delay must be positive");
            }
        }

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new ProducerThread(topic, generateProperties(), delay)).start();
        }

    }

    private Properties generateProperties(){
        Properties props = new Properties();

        props.put(BOOTSTRAP_SERVERS, DEFAULT_SERVER);
        props.put(ACKS, ALL);
        props.put(DELIVERY_TIMEOUT_MS, DEVAULT_TIMEOUT);
        props.put(BATCH_SIZE, DEFAULT_BATCH_SIZE);
        props.put(BUFFER_MEMORY, DEFAULT_BUFFER_MEMORY);
        props.put(KEY_SERIALIZER, KEY_KAFKA_SERIALIZER);
        props.put(VALUE_SERIALIZER, VALUE_SERIALIZER);

        return props;
    }

    private static void initOptions() {

        Option topic = new Option(T, TOPIC, true, KAFKA_TOPIC_NAME);
        topic.setRequired(true);
        options.addOption(topic);

        Option numberOfThreads = new Option(THREADS, THREADS, true, THE_NUMBER_OF_THREADS_1_BY_DEFAULT);
        numberOfThreads.setRequired(false);
        options.addOption(numberOfThreads);

        Option delay = new Option(D, DELAY, true, TIME_PARAMETER_DESCRIPTION);
        delay.setRequired(false);
        options.addOption(delay);

    }

}
