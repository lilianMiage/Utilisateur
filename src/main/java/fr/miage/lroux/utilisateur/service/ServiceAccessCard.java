package fr.miage.lroux.utilisateur.service;

import fr.miage.lroux.utilisateur.entity.AccessCard;
import fr.miage.lroux.utilisateur.repository.RepoAccessCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAccessCard {

    @Autowired
    private RepoAccessCard repoAccessCard;

    public AccessCard createAccessCard(AccessCard accessCard) throws IllegalArgumentException {
        Optional<AccessCard> accessCardOptional = repoAccessCard.findById(accessCard.getCardId());
        if (accessCardOptional.isPresent()){
            throw new IllegalArgumentException("An access card with this ID already exist" + accessCard.getCardId());
        }
        repoAccessCard.save(accessCard);
        return accessCard;
    }

    public AccessCard getAccessCardById(Long id) throws Exception {
        Optional<AccessCard> accessCardOptional = repoAccessCard.findById(id);
        if (accessCardOptional.isEmpty()){
            throw new Exception("No access card exist with this ID " + id);
        }
        return accessCardOptional.get();
    }

    public void deleteAccessCardById(Long id) throws Exception {
        Optional<AccessCard> accessCardOptional = repoAccessCard.findById(id);
        if (accessCardOptional.isEmpty()){
            throw new Exception("No access card exist with this ID " + id);
        }
        repoAccessCard.deleteById(id);
    }
}
