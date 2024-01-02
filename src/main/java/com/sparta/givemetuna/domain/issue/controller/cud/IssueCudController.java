package com.sparta.givemetuna.domain.issue.controller.cud;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.issue.controller.validation.IssueValidator;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueDeleteResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueStatusUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueStatusUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.service.cud.IssueCudService;
import com.sparta.givemetuna.domain.issue.service.read.IssueReadService;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/issues")
@EnableWebMvc
public class IssueCudController {

	//	@Qualifier("issueJpaCudService")
	private final IssueCudService issueCudService;

	private final IssueReadService issueReadService;

	private final IssueValidator issueValidator;

//	private final CardService cardService;

	@PostMapping
	public ResponseEntity<IssueCreateResponseDto> createIssue(
		@RequestBody IssueCreateRequestDto createRequestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		/* Body 검증 :: Card 검증 *//**/
//		issueValidator.validateRole(
		// Card card = cardService.selectById(createRequestDto.getCardId());
		IssueCreateResponseDto responseDto = issueCudService.createIssue(
			createRequestDto,
			Card.builder().build(),
			User.builder().build()
		);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@PatchMapping("/{issue_id}/contents")
	public ResponseEntity<IssueUpdateResponseDto> updateIssue(
		@PathVariable("issue_id") long issueId,
		@RequestBody IssueUpdateRequestDto updateRequestDto
//		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		/* Header 검증 :: Issue 작성자 검증 */
		// User user = userDetails.getUser();
		Issue issue = issueReadService.selectById(issueId, User.builder().build());

		IssueUpdateResponseDto responseDto = issueCudService.updateIssue(
			updateRequestDto,
			issue
		);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@PatchMapping("/{issue_id}/status")
	public ResponseEntity<IssueStatusUpdateResponseDto> closeIssue(
		@PathVariable("issue_id") long issueId,
		@RequestBody IssueStatusUpdateRequestDto updateRequestDto
//		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		/* Header 검증 :: Issue 작성자 검증 */
		// User user = userDetails.getUser();
		Issue issue = issueReadService.selectById(issueId, User.builder().build());

		IssueStatusUpdateResponseDto responseDto = issueCudService.closeIssue(
			updateRequestDto,
			issue
		);

		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@DeleteMapping("/{issue_id}")
	public ResponseEntity<IssueDeleteResponseDto> deleteIssue(
		@PathVariable("issue_id") long issueId
//		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		/* Header 검증 :: Issue 작성자 검증 */
		// User user = userDetails.getUser();
		Issue issue = issueReadService.selectById(issueId, User.builder().build());

		IssueDeleteResponseDto responseDto = issueCudService.deleteIssue(
			issue
		);

		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
}

