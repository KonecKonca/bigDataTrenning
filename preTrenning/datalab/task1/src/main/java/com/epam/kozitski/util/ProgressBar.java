package com.epam.kozitski.util;

import org.springframework.stereotype.Component;

@Component
public class ProgressBar {
    private int counter = 1;

    public void iterationLog(int elementsNumber, String latitude, String longitude){
        System.out.println("... Loaded " + (counter++ * 100)/elementsNumber  + "% " +
                "received crimes from location: latitude=" + latitude + " longitude=" + longitude);
    }

}
