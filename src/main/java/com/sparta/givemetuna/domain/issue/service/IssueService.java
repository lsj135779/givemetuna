package com.sparta.givemetuna.domain.issue.service;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.IssueDeleteResponseDto;
import com.sparta.givemetuna.domain.issue.dto.IssueStatusUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueStatusUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.IssueUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.user.entity.User;
import java.time.LocalDateTime;

public interface IssueService {

	IssueCreateResponseDto createIssue(IssueCreateRequestDto issueCreateRequestDto, Card card, User user, LocalDateTime createdAt);

	IssueUpdateResponseDto updateIssue(IssueUpdateRequestDto updateRequestDto, Issue issue, LocalDateTime updatedAt);

	Issue selectByIdAndUser(long issueId);

	IssueStatusUpdateResponseDto closeIssue(IssueStatusUpdateRequestDto updateRequestDto, Issue issue, LocalDateTime now);

	IssueDeleteResponseDto deleteIssue(Issue issue);
}
