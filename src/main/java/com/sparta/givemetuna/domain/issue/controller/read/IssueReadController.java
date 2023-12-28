package com.sparta.givemetuna.domain.issue.controller.read;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.service.read.IssueReadService;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<Issue> getSingularIssue(
		@PathVariable("issue_id") long issueId
		//		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		Issue issue = issueReadService.selectByIdAndUser(issueId, User.builder().build());
		return ResponseEntity.status(HttpStatus.OK).body(issue);
	}
}

