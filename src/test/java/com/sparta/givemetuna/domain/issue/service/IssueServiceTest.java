package com.sparta.givemetuna.domain.issue.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.IssueStatusUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueStatusUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.IssueUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.Status;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.support.IntegrationTestSupport;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

class IssueServiceTest extends IntegrationTestSupport {

	@Autowired
	@Qualifier("issueJpaCrudService")
	private IssueService issueService;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("카드에 대한, 새로운 이슈를 생성한다.")
	public void 카드관련_새로운이슈_등록() {
		// GIVEN
		Card card = cardRepository.save(Card.builder().id(2L).build());
		User user = userRepository.save(User.builder().build());
		IssueCreateRequestDto requestDto = IssueCreateRequestDto.builder()
			.title("도메인이슈 : 업무 프로세스가 이상합니다")
			.contents("요청주신 업무 관련하여 프로세스에 대해 이해가 되지 않습니다.")
			.cardId(2L)
			.build();

		// WHEN
		LocalDateTime now = LocalDateTime.now();
		IssueCreateResponseDto responseDto = issueService.createIssue(requestDto, card, user, now);

		// THEN
		assertEquals(requestDto.getTitle(), responseDto.getTitle());
		assertEquals(requestDto.getContents(), responseDto.getContents());
		assertEquals(now, responseDto.getCreatedAt());
	}

	@Test
	@DisplayName("이슈에 대하여 이슈 내용을 수정한다.")
	public void 이슈_수정() {
		// GIVEN
		Card card1 = cardRepository.save(Card.builder().id(1L).build());
		Card card2 = cardRepository.save(Card.builder().id(2L).build());
		User user = userRepository.save(User.builder().build());
		Issue issue = Issue.builder()
			.id(1L)
			.title("도메인이슈 : 업무 프로세스가 이상합니다")
			.contents("요청주신 업무 관련하여 프로세스에 대해 이해가 되지 않습니다.")
			.status(Status.OPEN)
			.card(card1)
			.user(user)
			.build();
		issueRepository.save(issue);
		IssueUpdateRequestDto updateRequestDto = IssueUpdateRequestDto.builder()
			.title("도메인이슈 : 업무 프로세스가 수정요청드립니다.")
			.contents("요청주신 업무 관련하여 이상한 점을 발견했는데요. 혹시 이 부분에 대해서 이야기할 수 있을까요?")
			.cardId(2L)
			.build();

		// WHEN
		LocalDateTime now = LocalDateTime.now();
		IssueUpdateResponseDto updateResponseDto = issueService.updateIssue(updateRequestDto, issue, now);

		// THEN
		assertEquals(1L, updateResponseDto.getIssueId());
		assertEquals("도메인이슈 : 업무 프로세스가 수정요청드립니다.", updateResponseDto.getTitle());
		assertEquals("요청주신 업무 관련하여 이상한 점을 발견했는데요. 혹시 이 부분에 대해서 이야기할 수 있을까요?", updateResponseDto.getContents());
		assertEquals(now, updateResponseDto.getUpdatedAt());
		assertEquals(2L, updateResponseDto.getCardId());
	}

	@Test
	@DisplayName("이슈를 닫거나 엽니다.")
	public void 이슈_종료() {
		// GIVEN
		Card card1 = cardRepository.save(Card.builder().id(1L).build());
		User user = userRepository.save(User.builder().build());
		Issue issue = Issue.builder()
			.id(1L)
			.title("도메인이슈 : 업무 프로세스가 이상합니다")
			.contents("요청주신 업무 관련하여 프로세스에 대해 이해가 되지 않습니다.")
			.status(Status.OPEN)
			.card(card1)
			.user(user)
			.build();
		issueRepository.save(issue);
		IssueStatusUpdateRequestDto requestDto = IssueStatusUpdateRequestDto.builder()
			.status(Status.CLOSED)
			.build();

		// WHEN
		LocalDateTime now = LocalDateTime.now();
		IssueStatusUpdateResponseDto responseDto = issueService.closeIssue(requestDto, issue, now);

		// THEN
		assertEquals(1L, responseDto.getIssueId());
		assertEquals(Status.CLOSED, responseDto.getStatus());
		assertEquals(now, responseDto.getUpdatedAt());
	}
}