package fr.miage.lroux.utilisateur.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class AccessCard {

    @Id
    private long cardId;

    public AccessCard(long cardId, int password) {
        this.cardId = cardId;
        this.password = password;
    }

    private int password;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public AccessCard() {
    }
}
