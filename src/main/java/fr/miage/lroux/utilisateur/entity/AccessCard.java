package fr.miage.lroux.utilisateur.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class AccessCard {

    @Id
    private long cardId;

    private int password;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}
