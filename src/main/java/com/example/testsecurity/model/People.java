package com.example.testsecurity.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Data
public class People {
    @Id
    @GeneratedValue
    Long id;

    String name;
    String surname;
    String passport;

    @ManyToOne
    City city;

    String login;
    String password;
}
