package by.epam.producer;

import org.junit.Test;

public class MainTest {

    @Test(expected = IllegalArgumentException.class)
    public void testMainWithInvalidParameters() {
        Main.main(new String[] {
                "--topic", "topic",
                "--threads", "-1",
                "--delay", "0"
        });
    }

}