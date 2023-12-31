package com.sparta.givemetuna.domain.issue.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueDeleteResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueStatusUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueStatusUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueUpdateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.cud.IssueUpdateResponseDto;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.IssueStatus;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.issue.service.cud.IssueCudService;
import com.sparta.givemetuna.domain.support.IntegrationTestSupport;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class IssueCudServiceTest extends IntegrationTestSupport {

	@Autowired
//	@Qualifier("issueJpaCrudService")
	private IssueCudService issueCudService;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private UserRepository userRepository;

	@AfterEach
	void tearDown() {
		issueRepository.deleteAllInBatch();
		cardRepository.deleteAllInBatch();
		userRepository.deleteAllInBatch();
	}

	@Test
	@DisplayName("카드에 대한, 새로운 이슈를 생성한다.")
	public void 카드관련_새로운이슈_등록() {
		// GIVEN
		Card card = cardRepository.save(Card.builder().build());
		User user = userRepository.save(User.builder().build());
		IssueCreateRequestDto requestDto = IssueCreateRequestDto.builder()
			.title("도메인이슈 : 업무 프로세스가 이상합니다")
			.contents("요청주신 업무 관련하여 프로세스에 대해 이해가 되지 않습니다.")
			.cardId(card.getId())
			.build();

		// WHEN
		IssueCreateResponseDto responseDto = issueCudService.createIssue(requestDto, card, user);

		// THEN
		assertEquals(card.getId(), responseDto.getCardId());
		assertEquals(requestDto.getTitle(), responseDto.getTitle());
		assertEquals(requestDto.getContents(), responseDto.getContents());
	}

	@Test
	@DisplayName("이슈에 대하여 이슈 내용을 수정한다.")
	public void 이슈_수정() {
		// GIVEN
		Card card = cardRepository.save(Card.builder().build());
		User user = userRepository.save(User.builder().build());
		Issue issue = Issue.builder()
			.title("도메인이슈 : 업무 프로세스 수정요청드립니다.")
			.contents("요청주신 업무 관련하여 프로세스에 대해 이해가 되지 않습니다.")
			.issueStatus(IssueStatus.OPEN)
			.card(card)
			.user(user)
			.build();
		issueRepository.save(issue);
		IssueUpdateRequestDto updateRequestDto = IssueUpdateRequestDto.builder()
			.title("도메인이슈 : 업무 프로세스 수정요청드립니다.")
			.contents("요청주신 업무 관련하여 이상한 점을 발견했는데요. 혹시 이 부분에 대해서 이야기할 수 있을까요?")
			.cardId(card.getId())
			.build();

		// WHEN
		IssueUpdateResponseDto updateResponseDto = issueCudService.updateIssue(updateRequestDto, issue);

		// THEN
		assertEquals("도메인이슈 : 업무 프로세스 수정요청드립니다.", updateResponseDto.getTitle());
		assertEquals("요청주신 업무 관련하여 이상한 점을 발견했는데요. 혹시 이 부분에 대해서 이야기할 수 있을까요?", updateResponseDto.getContents());
	}

	@Test
	@DisplayName("이슈를 닫거나 엽니다.")
	public void 이슈_종료() {
		// GIVEN
		Card card = cardRepository.save(Card.builder().build());
		User user = userRepository.save(User.builder().build());
		Issue issue = Issue.builder()
			.title("도메인이슈 : 업무 프로세스가 이상합니다")
			.contents("요청주신 업무 관련하여 프로세스에 대해 이해가 되지 않습니다.")
			.issueStatus(IssueStatus.OPEN)
			.card(card)
			.user(user)
			.build();
		issueRepository.save(issue);
		IssueStatusUpdateRequestDto requestDto = IssueStatusUpdateRequestDto.builder()
			.issueStatus(IssueStatus.CLOSED)
			.build();

		// WHEN
		IssueStatusUpdateResponseDto responseDto = issueCudService.closeIssue(requestDto, issue);

		// THEN
		assertEquals(IssueStatus.CLOSED, responseDto.getIssueStatus());
	}

	@Test
	@DisplayName("이슈를 삭제합니다.")
	public void 이슈_삭제() {
		// GIVEN
		Card card1 = cardRepository.save(Card.builder().build());
		User user = userRepository.save(User.builder().build());
		Issue issue = Issue.builder()
			.title("도메인이슈 : 업무 프로세스가 이상합니다")
			.contents("요청주신 업무 관련하여 프로세스에 대해 이해가 되지 않습니다.")
			.issueStatus(IssueStatus.OPEN)
			.card(card1)
			.user(user)
			.build();
		issueRepository.save(issue);

		// WHEN
		IssueDeleteResponseDto deleteResponseDto = issueCudService.deleteIssue(issue);

		// THEN
		assertEquals(0, issueRepository.findAll().size());
		assertEquals(card1.getId(), deleteResponseDto.getIssueId());
	}
}