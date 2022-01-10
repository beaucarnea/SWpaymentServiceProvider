package de.othr.sw.paymentServiceProvider.entity;

import javax.persistence.Entity;

@Entity
public class Club extends User {
    private String clubId;
    private String clubName;

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
