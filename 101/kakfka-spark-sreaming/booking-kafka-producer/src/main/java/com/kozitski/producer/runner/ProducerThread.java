package com.kozitski.producer.runner;

import com.kozitski.producer.domain.BookingEvent;
import com.kozitski.producer.util.generator.BookingEventGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * The type Producer thread.
 */
@Slf4j
public class ProducerThread implements Runnable {

    private final String topic;
    private final Producer<String, BookingEvent> producer;
    private final int delay;
    @Getter@Setter
    private boolean isInterrupted = false;

    /**
     * Instantiates a new Producer thread.
     *
     * @param topic the topic
     * @param props the props
     * @param delay the delay
     */
    ProducerThread(String topic, Properties props, int delay) {
        this.topic = topic;
        this.producer = new KafkaProducer<>(props);
        this.delay = delay;
    }

    @Override
    public void run() {

        BookingEventGenerator eventGenerator = new BookingEventGenerator();

        while (!isInterrupted) {
            producer.send(new ProducerRecord<>(topic, eventGenerator.generate()));
            log.info("record was posted into topic...");

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                log.error("Producer thread was interrupted", e);
            }

        }

    }

}
