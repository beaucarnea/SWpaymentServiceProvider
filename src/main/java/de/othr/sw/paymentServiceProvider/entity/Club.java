package de.othr.sw.paymentServiceProvider.entity;

import javax.persistence.Entity;

@Entity
public class Club extends User {

    private String clubName;

    public Club(){

    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
