package com.sparta.givemetuna.domain.issuecomment.service.cud;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateRequestDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateResponseDto;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import com.sparta.givemetuna.domain.issuecomment.repository.IssueCommentRepository;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueCommentCudServiceImpl implements IssueCommentCudService {

	private final IssueCommentRepository issueCommentRepository;

	@Override
	public IssueCommentCreateResponseDto createIssueComment(User user, Issue issue, IssueCommentCreateRequestDto requestDto) {
		IssueComment issueComment = IssueComment.of(user, issue, requestDto);
		issueCommentRepository.save(issueComment);
		return IssueCommentCreateResponseDto.of(issueComment);
	}
}
