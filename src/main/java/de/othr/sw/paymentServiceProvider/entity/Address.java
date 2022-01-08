package de.othr.sw.paymentServiceProvider.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    private Long addressId;
    private String houseNumberAndStreet;
    private String location;
    private String postcode;
    private String country;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getHouseNumberAndStreet() {
        return houseNumberAndStreet;
    }

    public void setHouseNumberAndStreet(String houseNumberAndStreet) {
        this.houseNumberAndStreet = houseNumberAndStreet;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
