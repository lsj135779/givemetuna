package com.sparta.givemetuna.domain.issue.service;

import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("issueJpaSearchService")
@RequiredArgsConstructor
public class IssueSearchServiceImpl implements IssueSearchService {

//	private final CardService cardService;

	private final IssueRepository issueRepository;
}
