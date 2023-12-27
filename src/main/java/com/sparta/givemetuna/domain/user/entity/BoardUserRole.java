package com.sparta.givemetuna.domain.user.entity;

import com.sparta.givemetuna.domain.board.entity.Board;
import jakarta.persistence.*;

@Entity
@Table(name = "boardUserRole")
public class BoardUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
}
