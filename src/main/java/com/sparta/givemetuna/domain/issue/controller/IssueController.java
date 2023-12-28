package com.sparta.givemetuna.domain.issue.controller;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.issue.service.IssueService;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issues")
@EnableWebMvc
public class IssueController {

	@Qualifier("issueJpaCrudService")
	private final IssueService issueService;

//	private final CardService cardService;

	@PostMapping
	public ResponseEntity<IssueCreateResponseDto> createIssue(
		@RequestBody IssueCreateRequestDto issueCreateRequestDto
//		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
//		Card card = cardService.getCardById(issueCreateRequestDto.getCardId());
		IssueCreateResponseDto issueCreateResponseDto = issueService.createIssue(issueCreateRequestDto,
			Card.builder().build(),
			User.builder().build());
		return ResponseEntity.status(HttpStatus.CREATED).body(issueCreateResponseDto);
	}
}

