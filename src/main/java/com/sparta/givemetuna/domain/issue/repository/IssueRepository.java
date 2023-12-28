package com.sparta.givemetuna.domain.issue.repository;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}