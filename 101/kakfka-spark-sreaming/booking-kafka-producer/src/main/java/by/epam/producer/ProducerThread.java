package by.epam.producer;

import by.epam.producer.entity.BookingEvent;
import by.epam.producer.util.BookingEventGenerator;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerThread implements Runnable {

    private final String topic;
    private final Producer<String, BookingEvent> producer;
    private final int delay;

    public ProducerThread(String topic, Properties props, int delay) {
        this.topic = topic;
        this.producer = new KafkaProducer<>(props);
        this.delay = delay;
    }

    @Override
    public void run() {

        BookingEventGenerator eventGenerator = new BookingEventGenerator();

        while (true) {
            producer.send(new ProducerRecord<>(
                    topic,
                    eventGenerator.generate()
            ));

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
