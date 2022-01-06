package de.othr.sw.paymentServiceProvider.entity;

import javax.persistence.Entity;

@Entity
public class Club extends User {
    private String clubId;
    private String name;
}
