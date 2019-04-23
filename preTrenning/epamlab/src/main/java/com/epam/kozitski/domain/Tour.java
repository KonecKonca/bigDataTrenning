package com.epam.kozitski.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tour {
    private long id;
    private Base64 photo;
    private long date;
    private int duration;
    @Size(max = 1024)
    private String description;
    private double cost;
    private TourType tourType;
    private long hotel_id;
    private long country_id;
}
