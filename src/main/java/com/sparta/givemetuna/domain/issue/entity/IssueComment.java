package com.sparta.givemetuna.domain.issue.entity;

import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "issueComment")
public class IssueComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    private Issue issue;
}
