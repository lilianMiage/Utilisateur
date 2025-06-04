package fr.miage.lroux.utilisateur.controller;

import fr.miage.lroux.utilisateur.entity.User;
import fr.miage.lroux.utilisateur.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing user operations.
 * Provides endpoints to create, retrieve, and delete users.
 */
@RestController
@RequestMapping("/api/user")
public class ControllerUser {

    /**
     * Service for user operations.
     * This service handles the business logic for user management.
     */
    @Autowired
    private ServiceUser serviceUser;

    /**
     * Creates a new user.
     * @param user The user to be created.
     * @return The created user.
     * @throws Exception If an error occurs during user creation.
     */
    @PostMapping("/create")
    public User creaUser(@RequestBody User user) throws Exception{
        serviceUser.createUser(user);
        return user;
    }

    /**
     * Retrieves a user by their ID.
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID.
     * @throws Exception If the user does not exist.
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) throws Exception {
        return serviceUser.getUserById(id);
    }

    /**
     * Deletes a user by their ID.
     * @param id The ID of the user to delete.
     * @throws Exception If the user does not exist.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id) throws Exception {
        serviceUser.deleteUserById(id);
    }
}
