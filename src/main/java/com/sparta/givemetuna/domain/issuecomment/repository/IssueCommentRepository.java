package com.sparta.givemetuna.domain.issuecomment.repository;

import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueCommentRepository extends JpaRepository<IssueComment, Long> {

}
