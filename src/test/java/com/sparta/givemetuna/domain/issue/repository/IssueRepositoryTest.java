package com.sparta.givemetuna.domain.issue.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.Status;
import com.sparta.givemetuna.domain.support.IntegrationTestSupport;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class IssueRepositoryTest extends IntegrationTestSupport {

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("회원에 대한 원하는 이슈를 단건 조회합니다.")
	public void 회원_이슈_단건조회() {
		// GIVEN
		User user = userRepository.save(User.builder().id(1L).build());
		Issue issue = Issue.builder()
			.id(1L)
			.title("도메인이슈 : 업무 프로세스가 이상합니다")
			.contents("요청주신 업무 관련하여 프로세스에 대해 이해가 되지 않습니다.")
			.user(user)
			.status(Status.OPEN)
			.build();
		issueRepository.save(issue);

		// WHEN
		Issue foundIssue = issueRepository.findByIdAndUser(1L, user)
			.orElseThrow(() -> new RuntimeException("원하시는 Issue는 존재하지 않습니다."));

		// THEN
		assertEquals(1L, foundIssue.getId());
		assertEquals(user.getId(), foundIssue.getUser().getId());
	}
}