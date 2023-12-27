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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board",targetEntity = Stage.class,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Stage> stages = new ArrayList<>();

    /* Board 삭제 시, Card와 Checklist 삭제 쿼리를 구현해주어야함 @임지훈 */
}
