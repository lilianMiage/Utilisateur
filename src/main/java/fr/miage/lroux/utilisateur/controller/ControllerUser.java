package fr.miage.lroux.utilisateur.controller;

import fr.miage.lroux.utilisateur.entity.User;
import fr.miage.lroux.utilisateur.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class ControllerUser {

    @Autowired
    private ServiceUser serviceUser;

    @PostMapping("create")
    public User creaUser(@RequestBody User user) throws Exception{
        serviceUser.createUser(user);
        return user;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) throws Exception {
        return serviceUser.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id) throws Exception {
        serviceUser.deleteUserById(id);
    }
}
