package com.sparta.givemetuna.domain.issue.service.read;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.user.entity.User;

public interface IssueReadService {

	Issue selectByIdAndUser(long issueId, User user);
}
