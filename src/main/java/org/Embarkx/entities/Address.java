package org.Embarkx.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="address")
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}