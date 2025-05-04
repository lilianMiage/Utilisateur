package fr.miage.lroux.utilisateur.service;

import fr.miage.lroux.utilisateur.entity.User;
import fr.miage.lroux.utilisateur.repository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceUser {

    @Autowired
    private RepoUser repoUser;

    /**
     * TODO
     * @param user
     * @return
     * @throws Exception
     */
    public User createUser(User user) throws Exception {
        Optional<User> userOptional = repoUser.findById(user.getUserId());
        if (userOptional.isPresent()){
            throw new Exception("A user already with this ID" + user.getUserId());
        }
        repoUser.save(user);
        return user;
    }

    /**
     * TODO
     * @param id
     * @return
     * @throws Exception
     */
    public User getUserById(Long id) throws Exception{
        Optional<User> userOptional = repoUser.findById(id);
        if(userOptional.isEmpty()){
            throw new Exception("No user has been retrieved with this id " +id);
        }
        return userOptional.get();
    }

    /**
     * TODO
     * @param id
     * @throws Exception
     */
    public void deleteUserById(Long id) throws Exception{
        Optional<User> userOptional = repoUser.findById(id);
        if(userOptional.isEmpty()){
            throw new Exception("No user has been retrieved with this id " +id);
        }
        repoUser.deleteById(id);
    }
}
