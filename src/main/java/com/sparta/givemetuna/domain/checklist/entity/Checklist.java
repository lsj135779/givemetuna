package com.sparta.givemetuna.domain.checklist.entity;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.column.entity.Stage;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "checklist")
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contents;

    @Column
    private Boolean check;

    @Column
    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
