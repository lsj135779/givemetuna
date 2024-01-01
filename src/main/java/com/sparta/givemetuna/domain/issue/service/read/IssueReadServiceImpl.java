package com.sparta.givemetuna.domain.issue.service.read;

import com.sparta.givemetuna.domain.issue.dto.read.IssueReadResponseDto;
import com.sparta.givemetuna.domain.issue.dto.read.IssueSelectCondition;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("issueJpaReadService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IssueReadServiceImpl implements IssueReadService {

	private final IssueRepository issueRepository;

	@Override
	public Issue selectById(long issueId, User user) {
		return issueRepository.findByIdAndUser(issueId, user)
			.orElseThrow(() ->
				new RuntimeException("찾으시는 issue가 존재하지 않습니다.")); // 도메인 커스텀 예외로 변경 요망
	}

	@Override
	public IssueReadResponseDto getIssue(long issueId, User user) {
		Issue issue = selectById(issueId, user);
		return IssueReadResponseDto.of(issue);
	}

	@Override
	public Page<IssueReadResponseDto> getIssues(IssueSelectCondition condition, Pageable pageable) {
		return issueRepository.selectByConditionPaging(condition, pageable);
	}
}
