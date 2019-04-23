package com.epam.kozitski.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.net.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {
    private long id;
    @Size(max = 40)
    private String name;
    private short stars;
    @Size(max = 256)
    private URL website;
    private double latitude;
    private double longitude;
    private Features features;
}
