package com.epam.kozitski.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LondonStation {
    private String name;
    private double longitude;
    private double latitude;
}
