package com.sparta.givemetuna.domain.issuecomment.service.read;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import com.sparta.givemetuna.domain.user.entity.User;
import java.util.List;

public interface IssueCommentReadService {

	IssueComment selectBy(long commentId, Issue issue, User user);

	List<IssueComment> selectBy(Issue issue, User user);
}
