package com.sparta.givemetuna.domain.issuecomment.controller.cud;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.service.read.IssueReadService;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateRequestDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateResponseDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentDeleteResponseDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentUpdateRequestDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentUpdateResponseDto;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import com.sparta.givemetuna.domain.issuecomment.service.cud.IssueCommentCudService;
import com.sparta.givemetuna.domain.issuecomment.service.read.IssueCommentReadService;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.global.validator.BoardUserRoleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class IssueCommentCudController {

	private final BoardUserRoleValidator userRoleValidator;

	private final IssueCommentReadService issueCommentReadService;

	private final IssueCommentCudService issueCommentCudService;

	private final IssueReadService issueReadService;


	@PostMapping("/{issue_id}/comments")
	public ResponseEntity<IssueCommentCreateResponseDto> createIssueComment(
		@PathVariable("board_id") long boardId,
		@PathVariable("issue_id") long issueId,
		@RequestBody IssueCommentCreateRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		userRoleValidator.validateRole(this.getClass(), userDetails.getUser().getId(), boardId);
		User user = userDetails.getUser();
		Issue issue = issueReadService.selectById(issueId, user);
		IssueCommentCreateResponseDto responseDto = issueCommentCudService.createIssueComment(user, issue, requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@PostMapping("/{issue_id}/comments/{comment_id}")
	public ResponseEntity<IssueCommentUpdateResponseDto> updateIssueComment(
		@PathVariable("board_id") long boardId,
		@PathVariable("issue_id") long issueId,
		@PathVariable("comment_id") long commentId,
		@RequestBody IssueCommentUpdateRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		userRoleValidator.validateRole(this.getClass(), userDetails.getUser().getId(), boardId);
		User user = userDetails.getUser();
		Issue issue = issueReadService.selectById(issueId, user);
		IssueComment issueComment = issueCommentReadService.selectBy(commentId, issue, user);
		IssueCommentUpdateResponseDto responseDto = issueCommentCudService.updateIssueComment(issueComment, requestDto);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@DeleteMapping("/{issue_id}/comments/{comment_id}")
	public ResponseEntity<IssueCommentDeleteResponseDto> deleteIssueComment(
		@PathVariable("board_id") long boardId,
		@PathVariable("issue_id") long issueId,
		@PathVariable("comment_id") long commentId,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		userRoleValidator.validateRole(this.getClass(), userDetails.getUser().getId(), boardId);
		User user = userDetails.getUser();
		Issue issue = issueReadService.selectById(issueId, user);
		IssueComment issueComment = issueCommentReadService.selectBy(commentId, issue, user);
		IssueCommentDeleteResponseDto responseDto = issueCommentCudService.deleteIssueComment(issueComment);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
}

