package com.sparta.givemetuna.domain.issuecomment.controller.read;

import com.sparta.givemetuna.domain.issuecomment.dto.read.IssueCommentSelectCondition;
import com.sparta.givemetuna.domain.issuecomment.service.read.IssueCommentReadService;
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
public class IssueCommentReadController {

	private final IssueCommentReadService issueCommentReadService;


	@GetMapping("/{issue_id}/comments")
	public ResponseEntity<Page<IssueCommentReadResponseDto>> getPluralIssueComments(
		@PathVariable("issue_id") long issueId,
		@RequestBody IssueCommentSelectCondition condition,
		Pageable pageable
	) {
		Page<IssueCommentReadResponseDto> issueComments = issueCommentReadService.getIssueComments(issueId, condition, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(issueComments);
	}
}

