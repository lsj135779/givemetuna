package com.sparta.givemetuna.domain.card.entity;

import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "card",targetEntity = UserCard.class)
    private List<UserCard> userCards = new ArrayList<>();

    @OneToMany(mappedBy = "card",targetEntity = Checklist.class)
    private List<Checklist> checklists = new ArrayList<>();

    @OneToMany(mappedBy = "card",targetEntity = Issue.class)
    private List<Issue> issues = new ArrayList<>();
}
