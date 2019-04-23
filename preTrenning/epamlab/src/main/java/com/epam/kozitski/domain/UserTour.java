package com.epam.kozitski.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTour{
    private long user_id;
    private long tour_id;
}
