package com.sparta.givemetuna.domain.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.givemetuna.domain.column.entity.Stage;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board",targetEntity = Stage.class,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Stage> stages = new ArrayList<>();
}
