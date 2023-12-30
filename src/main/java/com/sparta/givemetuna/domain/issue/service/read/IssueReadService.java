package com.sparta.givemetuna.domain.issue.service.read;

import com.sparta.givemetuna.domain.issue.dto.read.IssueReadResponseDto;
import com.sparta.givemetuna.domain.issue.dto.read.IssueSelectCondition;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IssueReadService {

	Issue selectById(long issueId, User user);

	IssueReadResponseDto getIssue(long issueId, User user);

	Page<IssueReadResponseDto> getIssues(IssueSelectCondition condition, Pageable pageable);
}