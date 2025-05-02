package fr.miage.lroux.utilisateur.repository;

import fr.miage.lroux.utilisateur.entity.AccessCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoAccessCard extends CrudRepository<AccessCard, Long> {
}
