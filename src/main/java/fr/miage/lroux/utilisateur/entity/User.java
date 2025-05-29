package fr.miage.lroux.utilisateur.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a User.
 */
@Data
@Builder
@Entity
@Table(name = "utilisateur")
public class User {

    /**
     *  User ID.
     */
    @Id
    @GeneratedValue
    private long userId;
    /**
     *  User last name.
     */
    private String lastName;

    /**
     *  User first name.
     */
    private String firstName;

    /**
     *  Access card associated with the user.
     */
    @OneToOne(mappedBy = "user")
    private AccessCard accessCard;


    /**
     * Default constructor for JPA.
     */
    public User() {
    }

    /**
     * Constructor for User.
     *
     * @param lastName  User's last name
     * @param firstName User's first name
     */
    public User(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    /**
     * Constructor for User with userId, lastName, firstName and accessCard.
     *
     * @param userId    User's ID
     * @param lastName  User's last name
     * @param firstName User's first name
     * @param accessCard Access card associated with the user
     */
    public User(long userId, String lastName, String firstName, AccessCard accessCard) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.accessCard = accessCard;
    }

    /**
     * Get the user ID.
     *
     * @return User's ID
     */
    public long getUserId() {
        return userId;
    }


    /**
     * Get the last name of the user.
     *
     * @return User's last name
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * Get the first name of the user.
     *
     * @return User's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the access card associated with the user.
     *
     * @return Access card associated with the user
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Set the last name of the user.
     *
     * @param lastName User's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set the first name of the user.
     *
     * @param firstName User's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //TODO
    // private Voiture une seul par Utilisateur qui a une carte.
}
