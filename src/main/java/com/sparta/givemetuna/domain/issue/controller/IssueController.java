package com.sparta.givemetuna.domain.issue.controller;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.IssueUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.service.IssueService;
import com.sparta.givemetuna.domain.user.entity.User;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		@RequestBody IssueCreateRequestDto createRequestDto
//		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		/* Body 검증 :: Card 검증 */
		// Card card = cardService.selectById(createRequestDto.getCardId());
		IssueCreateResponseDto responseDto = issueService.createIssue(
			createRequestDto,
			Card.builder().build(),
			User.builder().build(),
			LocalDateTime.now()
		);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@PatchMapping("{issue_id}/contents")
	public ResponseEntity<IssueUpdateResponseDto> updateIssue(
		@PathVariable("issue_id") long issueId,
		@RequestBody IssueUpdateRequestDto updateRequestDto
//		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		/* Header 검증 :: Issue 작성자 검증 */
		// User user = userDetails.getUser();
		Issue issue = issueService.selectByIdAndUser(issueId);

		IssueUpdateResponseDto responseDto = issueService.updateIssue(
			updateRequestDto,
			issue,
			LocalDateTime.now()
		);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

}

