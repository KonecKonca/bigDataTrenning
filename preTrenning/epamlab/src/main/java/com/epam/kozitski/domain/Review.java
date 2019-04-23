package com.epam.kozitski.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    private long id;
    private long date;
    @Size(max = 1024)
    private String text;
    private long user_id;
    private long tour_id;
}
