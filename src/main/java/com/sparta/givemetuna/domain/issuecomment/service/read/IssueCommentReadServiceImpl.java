package com.sparta.givemetuna.domain.issuecomment.service.read;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issuecomment.controller.read.IssueCommentReadResponseDto;
import com.sparta.givemetuna.domain.issuecomment.dto.read.IssueCommentSelectCondition;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import com.sparta.givemetuna.domain.issuecomment.repository.IssueCommentRepository;
import com.sparta.givemetuna.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueCommentReadServiceImpl implements IssueCommentReadService {

	private final IssueCommentRepository issueCommentRepository;

	@Override
	public IssueComment selectBy(long commentId, Issue issue, User user) {
		return issueCommentRepository
			.findByIdAndIssueAndUser(commentId, issue, user)
			.orElseThrow(() -> new RuntimeException("이슈와 사용자 관련하여 찾으시는 댓글은 존재하지 않습니다."));
	}

	@Override
	public List<IssueComment> selectBy(Issue issue, User user) {
		return issueCommentRepository
			.findByIssueAndUser(issue, user);
	}

	@Override
	public Page<IssueCommentReadResponseDto> getIssueComments(Long issueId, IssueCommentSelectCondition condition, Pageable pageable) {
		return issueCommentRepository.selectByConditionPaging(issueId, condition, pageable);
	}
}
