package com.sparta.givemetuna.domain.user.entity;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.card.entity.UserCard;
import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.column.entity.Stage;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String account;

    @Column
    private String password;

    @Column
    @Email
    private String email;

    @Column
    private String nickname;

    @Column
    @Email
    private String github;

    @Column
    private String description;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Stage> stages = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Checklist> checklists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Issue> issues = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserCard> userCards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BoardUserRole> boardUserRoles = new ArrayList<>();
}
