package fr.miage.lroux.utilisateur.controller;

import fr.miage.lroux.utilisateur.entity.AccessCard;
import fr.miage.lroux.utilisateur.service.ServiceAccessCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accessCard/")
public class ControllerAccessCard {

    @Autowired
    private ServiceAccessCard serviceAccessCard;

    @PostMapping("create")
    public AccessCard createAccessCard(@RequestBody AccessCard accessCard) throws Exception{
        return serviceAccessCard.createAccessCard(accessCard);
    }

    @GetMapping("/{id}")
    public AccessCard getAccessCardById(@PathVariable Long id) throws Exception {
        return serviceAccessCard.getAccessCardById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccessCardById(@PathVariable Long id) throws Exception {
        serviceAccessCard.deleteAccessCardById(id);
    }
}
