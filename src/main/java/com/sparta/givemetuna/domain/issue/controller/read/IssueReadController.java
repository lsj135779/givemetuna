package com.sparta.givemetuna.domain.issue.controller.read;

import com.sparta.givemetuna.domain.issue.dto.read.IssueReadResponseDto;
import com.sparta.givemetuna.domain.issue.dto.read.IssueSelectCondition;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.service.read.IssueReadService;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.global.validator.BoardUserRoleValidator;
import com.sparta.givemetuna.global.validator.OrderCriteriaValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{board_id}/issues")
@EnableWebMvc
public class IssueReadController {

	private final BoardUserRoleValidator userRoleValidator;

	private final IssueReadService issueReadService;

	@GetMapping("/{issue_id}")
	public ResponseEntity<IssueReadResponseDto> getSingularIssue(
		@PathVariable("board_id") long boardId,
		@PathVariable("issue_id") long issueId,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		userRoleValidator.validateRole(this.getClass(), userDetails.getUser().getId(), boardId);
		IssueReadResponseDto readResponseDto = issueReadService.getIssue(issueId, userDetails.getUser());
		return ResponseEntity.status(HttpStatus.OK).body(readResponseDto);
	}

	@GetMapping
	public ResponseEntity<Page<IssueReadResponseDto>> getPluralIssues(
		@PathVariable("board_id") long boardId,
		@RequestBody IssueSelectCondition condition,
		Pageable pageable,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		List<String> orderCriterias = pageable.getSort().stream()
			.map(Order::getProperty)
			.toList();
		OrderCriteriaValidator.validateOrderCriteria(Issue.class, orderCriterias);
		userRoleValidator.validateRole(this.getClass(), userDetails.getUser().getId(), boardId);
		Page<IssueReadResponseDto> issues = issueReadService.getIssues(condition, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(issues);
	}
}

