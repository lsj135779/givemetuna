package com.sparta.givemetuna.domain.issue.repository;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

	Optional<Issue> findByIdAndUser(long issueId, User user);

}