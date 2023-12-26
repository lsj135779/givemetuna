package com.sparta.givemetuna.domain.column.entity;

import com.sparta.givemetuna.domain.card.entity.UserCard;
import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "column")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String category;

    @OneToMany(mappedBy = "UserCard")
    private List<UserCard> userCards = new ArrayList<>();

    @OneToMany(mappedBy = "Checklist")
    private List<Checklist> checklists = new ArrayList<>();

}
