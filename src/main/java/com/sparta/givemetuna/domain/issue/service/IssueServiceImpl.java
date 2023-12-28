package com.sparta.givemetuna.domain.issue.service;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.IssueUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("issueJpaCrudService")
@RequiredArgsConstructor
@Transactional
public class IssueServiceImpl implements IssueService {

//	private final CardService cardService;

	private final IssueRepository issueRepository;

	@Override
	public IssueCreateResponseDto createIssue(IssueCreateRequestDto issueCreateRequestDto, Card card, User user, LocalDateTime createdAt) {
		Issue issue = Issue.of(issueCreateRequestDto, card, user, createdAt);
		Issue savedIssue = issueRepository.save(issue);

		return IssueCreateResponseDto.of(savedIssue);
	}

	@Override
	public IssueUpdateResponseDto updateIssue(IssueUpdateRequestDto updateRequestDto, Issue issue, LocalDateTime updatedAt) {
		// 요청에 대하여 card를 찾는다. 없는 경우 예외처리를 한다.
		// Card card = cardService.selectById(updateRequestDto.getCardId());
		Card card = Card.builder()
			.id(updateRequestDto.getCardId())
			.build();

		// 찾은 Issue에 대해 request에 대한 update를 호출한다.
		issue.update(updateRequestDto, card, updatedAt);

		// 수정한 issue를 responsedto로 변환 후 반환한다.
		return IssueUpdateResponseDto.of(issue);
	}

	@Override
	public Issue selectByIdAndUser(long issueId) {
		return issueRepository.findById(issueId).orElseThrow(
			() -> new RuntimeException("찾으시는 issue가 존재하지 않습니다.")); // 도메인 커스텀 예외로 변경 요망
	}
}
