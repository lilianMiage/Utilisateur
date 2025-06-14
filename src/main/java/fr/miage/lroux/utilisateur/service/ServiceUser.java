package fr.miage.lroux.utilisateur.service;

import fr.miage.lroux.utilisateur.entity.User;
import fr.miage.lroux.utilisateur.repository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing users.
 * Provides methods to create, retrieve, and delete users.
 */
@Service
public class ServiceUser {

    /**
     * Repository for User operations.
     * This repository is used to interact with the database for User entities.
     */
    @Autowired
    private RepoUser repoUser;

    /**
     * Creates a new user.
     * @param user The user to be created.
     * @return The created user.
     * @throws Exception If a user with the same ID already exists.
     */
    public User createUser(User user) throws Exception {
        Optional<User> userOptional = repoUser.findById(user.getUserId());
        if (userOptional.isPresent()){
            throw new Exception("A user already exists with this ID" + user.getUserId());
        }
        repoUser.save(user);
        return user;
    }

    /**
     * Retrieves a user by their ID.
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID.
     * @throws Exception If no user exists with the given ID.
     */
    public User getUserById(Long id) throws Exception{
        Optional<User> userOptional = repoUser.findById(id);
        if(userOptional.isEmpty()){
            throw new Exception("No user has been retrieved with this id " +id);
        }
        return userOptional.get();
    }

    /**
     * Deletes a user by their ID.
     * @param id The ID of the user to delete.
     * @throws Exception If no user exists with the given ID.
     */
    public void deleteUserById(Long id) throws Exception{
        Optional<User> userOptional = repoUser.findById(id);
        if(userOptional.isEmpty()){
            throw new Exception("No user has been retrieved with this id " +id);
        }
        repoUser.deleteById(id);
    }

    /** * Updates the access card ID for a user.
     *
     * @param userid The ID of the user to update.
     * @param accessCardId The new access card ID to set for the user.
     * @return The updated user.
     * @throws Exception If no user exists with the given ID.
     */
    public User updateAccessCardId(Long userid,long accessCardId) throws Exception {
        User user = getUserById(userid);
        user.setAccessCardId(accessCardId);
        repoUser.save(user);
        return user;
    }

}
