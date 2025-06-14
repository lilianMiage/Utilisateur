package fr.miage.lroux.utilisateur.repository;

import fr.miage.lroux.utilisateur.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entity.
 * This interface extends CrudRepository to provide CRUD operations for User entities.
 */
@Repository
public interface RepoUser extends CrudRepository<User,Long> {
}
