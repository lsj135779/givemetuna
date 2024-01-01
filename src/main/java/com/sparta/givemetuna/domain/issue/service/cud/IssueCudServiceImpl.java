package com.sparta.givemetuna.domain.issue.service.cud;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueDeleteResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueStatusUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueStatusUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("issueJpaCudService")
@RequiredArgsConstructor
@Transactional
public class IssueCudServiceImpl implements IssueCudService {

//	private final CardService cardService;

	private final IssueRepository issueRepository;

	@Override
	public IssueCreateResponseDto createIssue(IssueCreateRequestDto issueCreateRequestDto, Card card, User user) {
		Issue issue = Issue.of(issueCreateRequestDto, card, user);
		Issue savedIssue = issueRepository.save(issue);

		return IssueCreateResponseDto.of(savedIssue);
	}

	@Override
	public IssueUpdateResponseDto updateIssue(IssueUpdateRequestDto updateRequestDto, Issue issue) {
		// 요청에 대하여 card를 찾는다. 없는 경우 예외처리를 한다.
		// Card card = cardService.selectById(updateRequestDto.getCardId());
		Card card = Card.builder()
			.id(updateRequestDto.getCardId())
			.build();

		// 찾은 Issue에 대해 request에 대한 update를 호출한다.
		issue.update(updateRequestDto, card);

		// 수정한 issue를 responsedto로 변환 후 반환한다.
		return IssueUpdateResponseDto.of(issue);
	}

	@Override
	public IssueStatusUpdateResponseDto closeIssue(IssueStatusUpdateRequestDto updateRequestDto, Issue issue) {
		issue.close(updateRequestDto.getIssueStatus());

		return IssueStatusUpdateResponseDto.of(issue);
	}

	@Override
	public IssueDeleteResponseDto deleteIssue(Issue issue) {
		issueRepository.delete(issue);

		return IssueDeleteResponseDto.of(issue.getId());
	}
}
