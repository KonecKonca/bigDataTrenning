package com.kozitski.producer;

import com.kozitski.producer.runner.ProducerApplicationRunner;
import org.junit.Test;

public class ApplicationTest {

    @Test(expected = IllegalArgumentException.class)
    public void testMainWithInvalidParameters() {
        new ProducerApplicationRunner().start(new String[] {
                "--topic", "topic",
                "--threads", "-1",
                "--delay", "0"
        });
    }

}