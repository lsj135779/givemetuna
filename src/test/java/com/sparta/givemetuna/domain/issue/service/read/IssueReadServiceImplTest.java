package com.sparta.givemetuna.domain.issue.service.read;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.Status;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.support.IntegrationTestSupport;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
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

	@Test
	@DisplayName("회원이 작성한 이슈를 단건 조회합니다.")
	public void 회원작성자_이슈_단건조회() {
		// GIVEN
		Card card = cardRepository.save(Card.builder().id(1L).build());
		User user = userRepository.save(User.builder().id(1L).build());
		Issue issue = Issue.builder()
			.id(1L)
			.title("도메인이슈 : 업무 프로세스가 이상합니다")
			.contents("요청주신 업무 관련하여 프로세스에 대해 이해가 되지 않습니다.")
			.user(user)
			.card(card)
			.status(Status.OPEN)
			.build();
		issueRepository.save(issue);

		// WHEN
		Issue foundIssue = issueReadService.selectByIdAndUser(1L, user);

		// THEN
		assertEquals(issue, foundIssue);
		assertEquals(card, foundIssue.getCard());
		assertEquals(user, foundIssue.getUser());
	}
}