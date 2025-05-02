package fr.miage.lroux.utilisateur.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@Entity
public class User {

    @Id
    private long userId;

    private String lastName;

    private String firstName;


    public User() {
    }

    public User(long userId, String lastName, String firstName) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public long getUserId() {
        return userId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    //TODO
    // private Voiture une seul par Utilisateur qui a une carte.
}
