package com.sparta.givemetuna.domain.card.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.sql.Timestamp;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Integer priority;

    @Column
    private Boolean isDone;

    @Column
    private Timestamp startedAt;

    @Column
    private Timestamp closedAt;
}
