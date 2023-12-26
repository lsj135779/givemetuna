package com.sparta.givemetuna.domain.user.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userRole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean g_manager;

    @Column
    private Boolean manager;

    @Column
    private Boolean user;

    @OneToMany(mappedBy = "userRole")
    private List<BoardUserRole> boardUserRoles = new ArrayList<>();
}
