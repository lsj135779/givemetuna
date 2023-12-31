package com.sparta.givemetuna.domain.issuecomment.service.cud;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateRequestDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateResponseDto;
import com.sparta.givemetuna.domain.user.entity.User;

public interface IssueCommentCudService {

	IssueCommentCreateResponseDto createIssueComment(User user, Issue issue, IssueCommentCreateRequestDto requestDto);
}
