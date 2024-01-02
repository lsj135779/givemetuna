package com.sparta.givemetuna.domain.issue.controller.cud;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.service.CardService;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueDeleteResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueStatusUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueStatusUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.exception.SelectIssueNotFoundException;
import com.sparta.givemetuna.domain.issue.service.cud.IssueCudService;
import com.sparta.givemetuna.domain.issue.service.read.IssueReadService;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.global.validator.BoardUserRoleValidator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{board_id}/issues")
@EnableWebMvc
@SecurityRequirement(name = "Bearer Authentication")
public class IssueCudController {

	private final BoardUserRoleValidator userRoleValidator;

	private final CardService cardService;

	@Qualifier("issueJpaCudService")
	private final IssueCudService issueCudService;

	@Qualifier("issueJpaReadService")
	private final IssueReadService issueReadService;

	@PostMapping
	public ResponseEntity<IssueCreateResponseDto> createIssue(
		@PathVariable("board_id") long boardId,
		@RequestBody IssueCreateRequestDto createRequestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		userRoleValidator.validateRole(this.getClass(), userDetails.getUser().getId(), boardId);
		Card card = cardService.checkCard(createRequestDto.getCardId());
		IssueCreateResponseDto responseDto = issueCudService.createIssue(
			createRequestDto,
			card,
			userDetails.getUser()
		);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@PatchMapping("/{issue_id}/contents")
	public ResponseEntity<IssueUpdateResponseDto> updateIssue(
		@PathVariable("board_id") long boardId,
		@PathVariable("issue_id") long issueId,
		@RequestBody IssueUpdateRequestDto updateRequestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) throws SelectIssueNotFoundException {
		userRoleValidator.validateRole(this.getClass(), userDetails.getUser().getId(), boardId);
		User user = userDetails.getUser();
		Issue issue = issueReadService.selectById(issueId, user);

		IssueUpdateResponseDto responseDto = issueCudService.updateIssue(
			updateRequestDto,
			issue
		);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@PatchMapping("/{issue_id}/status")
	public ResponseEntity<IssueStatusUpdateResponseDto> closeIssue(
		@PathVariable("board_id") long boardId,
		@PathVariable("issue_id") long issueId,
		@RequestBody IssueStatusUpdateRequestDto updateRequestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) throws SelectIssueNotFoundException {
		userRoleValidator.validateRole(this.getClass(), userDetails.getUser().getId(), boardId);
		User user = userDetails.getUser();
		Issue issue = issueReadService.selectById(issueId, user);

		IssueStatusUpdateResponseDto responseDto = issueCudService.closeIssue(
			updateRequestDto,
			issue
		);

		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@DeleteMapping("/{issue_id}")
	public ResponseEntity<IssueDeleteResponseDto> deleteIssue(
		@PathVariable("board_id") long boardId,
		@PathVariable("issue_id") long issueId,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) throws SelectIssueNotFoundException {
		userRoleValidator.validateRole(this.getClass(), userDetails.getUser().getId(), boardId);
		User user = userDetails.getUser();
		Issue issue = issueReadService.selectById(issueId, user);

		IssueDeleteResponseDto responseDto = issueCudService.deleteIssue(
			issue
		);

		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
}

