package com.sparta.givemetuna.domain.issue.validator;

import com.sparta.givemetuna.domain.card.service.CardService;
import com.sparta.givemetuna.domain.issue.service.read.IssueReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueValidatorService {

	private final IssueReadService issueReadService;

	private CardService cardService;


}
