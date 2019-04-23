package com.epam.kozitski.domain;

import lombok.*;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private long id;
    @Size(max = 40)
    private String login;
    @Size(min = 5, max = 40)
    private String password;
}
