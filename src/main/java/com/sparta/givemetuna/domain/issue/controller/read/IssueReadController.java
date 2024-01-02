package com.sparta.givemetuna.domain.issue.controller.read;

import com.sparta.givemetuna.domain.issue.dto.read.IssueReadResponseDto;
import com.sparta.givemetuna.domain.issue.dto.read.IssueSelectCondition;
import com.sparta.givemetuna.domain.issue.exception.SelectIssueNotFoundException;
import com.sparta.givemetuna.domain.issue.service.read.IssueReadService;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issues")
@EnableWebMvc
public class IssueReadController {

	private final IssueReadService issueReadService;

	@GetMapping("/{issue_id}")
	public ResponseEntity<IssueReadResponseDto> getSingularIssue(
		@PathVariable("issue_id") long issueId
		//		@AuthenticationPrincipal UserDetailsImpl userDetails
	) throws SelectIssueNotFoundException {
		IssueReadResponseDto readResponseDto = issueReadService.getIssue(issueId, User.builder().build());
		return ResponseEntity.status(HttpStatus.OK).body(readResponseDto);
	}

	@GetMapping
	public ResponseEntity<Page<IssueReadResponseDto>> getPluralIssues(
		@RequestBody IssueSelectCondition condition,
		Pageable pageable
	) {
		Page<IssueReadResponseDto> issues = issueReadService.getIssues(condition, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(issues);
	}
}

