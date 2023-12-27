package com.sparta.givemetuna.domain.column.entity;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.card.entity.UserCard;
import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "column")
//@Table(name = "column")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String category;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "stage",targetEntity = UserCard.class)
    private List<UserCard> userCards = new ArrayList<>();

    @OneToMany(mappedBy = "stage",targetEntity = Checklist.class)
    private List<Checklist> checklists = new ArrayList<>();

}
