package com.sparta.givemetuna.domain.issuecomment.controller.read;

import com.sparta.givemetuna.domain.issue.dto.read.IssueReadResponseDto;
import com.sparta.givemetuna.domain.issue.dto.read.IssueSelectCondition;
import com.sparta.givemetuna.domain.issue.service.read.IssueReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issues")
@EnableWebMvc
public class IssueCommentReadController {

	private final IssueReadService issueReadService;


	@GetMapping("/{issue_id}/comments")
	public ResponseEntity<Page<IssueReadResponseDto>> getPluralIssueComments(
		@RequestBody IssueSelectCondition condition,
		Pageable pageable
	) {
		return null;
	}
}

