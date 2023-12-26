package com.sparta.givemetuna.domain.checklist.entity;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
