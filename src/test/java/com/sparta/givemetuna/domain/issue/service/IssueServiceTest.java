package com.sparta.givemetuna.domain.issue.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateResponseDto;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.support.IntegrationTestSupport;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
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
		IssueCreateRequestDto requestDto = IssueCreateRequestDto.builder()
			.title("도메인이슈 : 업무 프로세스가 이상합니다")
			.contents("요청주신 업무 관련하여 프로세스에 대해 이해가 되지 않습니다.")
			.cardId(2L)
			.build();
		Card card = cardRepository.save(Card.builder().id(2L).build());
		User user = userRepository.save(User.builder().build());

		// WHEN
		IssueCreateResponseDto responseDto = issueService.createIssue(requestDto, card, user);

		// THEN
		assertEquals(requestDto.getTitle(), responseDto.getTitle());
		assertEquals(requestDto.getContents(), responseDto.getContents());
	}
}