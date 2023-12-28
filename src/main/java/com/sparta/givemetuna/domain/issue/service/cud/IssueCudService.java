package com.sparta.givemetuna.domain.issue.service.cud;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueDeleteResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueStatusUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueStatusUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.user.entity.User;
import java.time.LocalDateTime;

public interface IssueCudService {

	IssueCreateResponseDto createIssue(IssueCreateRequestDto issueCreateRequestDto, Card card, User user, LocalDateTime createdAt);

	IssueUpdateResponseDto updateIssue(IssueUpdateRequestDto updateRequestDto, Issue issue, LocalDateTime updatedAt);

	IssueStatusUpdateResponseDto closeIssue(IssueStatusUpdateRequestDto updateRequestDto, Issue issue, LocalDateTime now);

	IssueDeleteResponseDto deleteIssue(Issue issue);
}
