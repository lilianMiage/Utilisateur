package fr.miage.lroux.utilisateur.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
@Builder
public class User {

    @Id
    private int userId;

    private String nom;

    private String Prenom;

    @OneToOne
    @JoinColumn(name = "carteId")
    private Carte carte;

    //TODO
    // private Voiture une seul par Utilisateur qui a une carte.
}
