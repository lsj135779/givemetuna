package com.sparta.givemetuna.domain.issue.service;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.user.entity.User;

public interface IssueService {

	IssueCreateResponseDto createIssue(IssueCreateRequestDto issueCreateRequestDto, Card card, User user);
}
