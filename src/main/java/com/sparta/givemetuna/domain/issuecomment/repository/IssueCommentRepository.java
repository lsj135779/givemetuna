package com.sparta.givemetuna.domain.issuecomment.repository;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import com.sparta.givemetuna.domain.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueCommentRepository extends JpaRepository<IssueComment, Long> {

	Optional<IssueComment> findByIdAndIssueAndUser(long commentId, Issue issue, User user);

	List<IssueComment> findByIssueAndUser(Issue issue, User user);
}
