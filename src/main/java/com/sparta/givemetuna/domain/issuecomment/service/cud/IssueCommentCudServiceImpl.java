package com.sparta.givemetuna.domain.issuecomment.service.cud;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateRequestDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateResponseDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentDeleteResponseDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentUpdateRequestDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentUpdateResponseDto;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import com.sparta.givemetuna.domain.issuecomment.repository.IssueCommentRepository;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class IssueCommentCudServiceImpl implements IssueCommentCudService {

	private final IssueCommentRepository issueCommentRepository;

	@Override
	public IssueCommentCreateResponseDto createIssueComment(User user, Issue issue, IssueCommentCreateRequestDto requestDto) {
		IssueComment issueComment = IssueComment.of(user, issue, requestDto);
		issueCommentRepository.save(issueComment);
		return IssueCommentCreateResponseDto.of(issueComment);
	}

	@Override
	public IssueCommentUpdateResponseDto updateIssueComment(IssueComment issueComment, IssueCommentUpdateRequestDto requestDto) {
		issueComment.update(requestDto);
		return IssueCommentUpdateResponseDto.of(issueComment);
	}

	@Override
	public IssueCommentDeleteResponseDto deleteIssueComment(IssueComment issueComment) {
		issueComment.getIssue().getIssueComments().remove(issueComment);
		issueCommentRepository.delete(issueComment);
		return IssueCommentDeleteResponseDto.of(issueComment);
	}
}
