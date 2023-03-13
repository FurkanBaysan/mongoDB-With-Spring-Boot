package com.mongoDbWithSpringBoot.mongoWithSpringBoot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String country;
    private String postCode;
    private String city;
}
