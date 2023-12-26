package com.sparta.givemetuna.domain.user.entity;

import com.sparta.givemetuna.domain.board.entity.Board;
import jakarta.persistence.*;

@Entity
@Table(name = "boardUserRole")
public class BoardUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
