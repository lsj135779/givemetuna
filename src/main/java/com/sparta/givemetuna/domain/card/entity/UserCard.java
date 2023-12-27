package com.sparta.givemetuna.domain.card.entity;

import com.sparta.givemetuna.domain.column.entity.Stage;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.entity.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name = "userCard")
public class UserCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
