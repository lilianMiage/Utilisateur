package fr.miage.lroux.utilisateur.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class AccessCard {

    @Id
    @GeneratedValue
    private long cardId;

    public AccessCard(int password) {
        this.password = password;
    }

    private int password;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public AccessCard() {
    }

    public long getCardId() {
        return cardId;
    }

    public int getPassword() {
        return password;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
