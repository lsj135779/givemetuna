package com.sparta.givemetuna.domain.issue.service;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("issueJpaCrudService")
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

//	private final CardService cardService;

	private final IssueRepository issueRepository;

	@Override
	public IssueCreateResponseDto createIssue(IssueCreateRequestDto issueCreateRequestDto, Card card, User user) {
		Issue issue = Issue.of(issueCreateRequestDto, card, user);
		Issue savedIssue = issueRepository.save(issue);

		return IssueCreateResponseDto.of(savedIssue);
	}
}
