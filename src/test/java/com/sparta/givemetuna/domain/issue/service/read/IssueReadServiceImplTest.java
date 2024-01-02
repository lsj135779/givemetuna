package com.sparta.givemetuna.domain.issue.service.read;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.issue.dto.read.IssueReadResponseDto;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.IssueStatus;
import com.sparta.givemetuna.domain.issue.exception.SelectIssueNotFoundException;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.support.IntegrationTestSupport;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class IssueReadServiceImplTest extends IntegrationTestSupport {

	@Autowired
	private IssueReadService issueReadService;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CardRepository cardRepository;

	private Card card;

	private User user;

	private Issue issue;

	@BeforeEach
	void setUp() {
		card = cardRepository.save(Card.builder().title("요청업무#1").cardPriority(CardPriority.HIGH).build());
		user = userRepository.save(User.builder().build());
		issue = Issue.builder()
			.title("도메인이슈 : 업무 프로세스 수정요청드립니다.")
			.contents("요청주신 업무 관련하여 프로세스에 대해 이해가 되지 않습니다.")
			.issueStatus(IssueStatus.OPEN)
			.card(card)
			.user(user)
			.build();
		issueRepository.save(issue);
	}

	@Test
	@DisplayName("회원이 작성한 이슈를 단건 조회합니다.")
	public void 회원작성자_이슈_단건조회() throws SelectIssueNotFoundException {
		// WHEN
		IssueReadResponseDto responseDto = issueReadService.getIssue(issue.getId(), user);

		// THEN
		assertEquals(issue.getId(), responseDto.getId());
		assertEquals(card.getId(), responseDto.getCardId());
	}
}