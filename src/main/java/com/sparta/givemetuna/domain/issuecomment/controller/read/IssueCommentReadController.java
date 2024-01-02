package com.sparta.givemetuna.domain.issuecomment.controller.read;

import com.sparta.givemetuna.domain.issuecomment.dto.read.IssueCommentReadResponseDto;
import com.sparta.givemetuna.domain.issuecomment.dto.read.IssueCommentSelectCondition;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import com.sparta.givemetuna.domain.issuecomment.service.read.IssueCommentReadService;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.global.validator.BoardUserRoleValidator;
import com.sparta.givemetuna.global.validator.OrderCriteriaValidator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "Bearer Authentication")
public class IssueCommentReadController {

	private final BoardUserRoleValidator userRoleValidator;

	private final IssueCommentReadService issueCommentReadService;


	@GetMapping("/{issue_id}/comments")
	public ResponseEntity<Page<IssueCommentReadResponseDto>> getPluralIssueComments(
		@PathVariable("board_id") long boardId,
		@PathVariable("issue_id") long issueId,
		@RequestBody IssueCommentSelectCondition condition,
		Pageable pageable,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		List<String> orderCriterias = pageable.getSort().stream()
			.map(Order::getProperty)
			.toList();
		OrderCriteriaValidator.validateOrderCriteria(IssueComment.class, orderCriterias);
		userRoleValidator.validateRole(this.getClass(), userDetails.getUser().getId(), boardId);
		Page<IssueCommentReadResponseDto> issueComments = issueCommentReadService.getIssueComments(issueId, condition, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(issueComments);
	}
}

