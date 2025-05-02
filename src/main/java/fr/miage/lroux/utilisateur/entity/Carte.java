package fr.miage.lroux.utilisateur.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Carte {

    @Id
    private int carteId;

    private int code;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}
