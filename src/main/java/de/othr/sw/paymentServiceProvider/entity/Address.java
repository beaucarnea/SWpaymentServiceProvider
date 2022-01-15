package de.othr.sw.paymentServiceProvider.entity;

import de.othr.sw.paymentServiceProvider.entity.util.SingleIdEntity;

import javax.persistence.*;

//@Entity
@Embeddable
public class Address {
    //@Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private Long addressId;
    private String streetAndHousenumber;
    private String location;
    private String postcode;
    private String country;

    public Address(){

    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreetAndHousenumber() {
        return streetAndHousenumber;
    }

    public void setStreetAndHousenumber(String houseNumberAndStreet) {
        this.streetAndHousenumber = houseNumberAndStreet;
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
