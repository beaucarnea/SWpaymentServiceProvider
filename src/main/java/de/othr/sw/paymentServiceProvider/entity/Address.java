package de.othr.sw.paymentServiceProvider.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    private Long addressId;
    private Integer houseNumber;
    private String location;
    private String postcode;
    private String country;
}
