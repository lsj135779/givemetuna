package com.sparta.givemetuna.domain.issuecomment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.configuration.QueryDslConfig;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.IssueStatus;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.issuecomment.controller.read.IssueCommentReadResponseDto;
import com.sparta.givemetuna.domain.issuecomment.dto.read.IssueCommentSelectCondition;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import com.sparta.givemetuna.global.config.JpaAuditingConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({QueryDslConfig.class, JpaAuditingConfig.class})
class IssueCommentCustomRepositoryImplTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private IssueCommentRepository issueCommentRepository;

	@Autowired
	private IssueCommentCustomRepositoryImpl issueCommentCustomRepository;

	@Autowired
	private QueryDslConfig qd;

	private Issue savedIssue;

	@BeforeEach
	void setUp() {
		User user = userRepository.save(User.builder().build());
		Card card = cardRepository.save(Card.builder().build());
		List<Issue> issues = new ArrayList<>();
		Issue issue = Issue.builder()
			.title(String.format("도메인이슈 #%d", 1))
			.contents(String.format("요청 업무 프로세스 이슈 #%d 에 관하여 여쭤보고 싶습니다.", 1))
			.user(user)
			.card(card)
			.issueStatus(IssueStatus.OPEN)
			.build();
		savedIssue = issueRepository.save(issue);
		IssueComment issueComment = IssueComment.builder()
			.user(user)
			.issue(issue)
			.contents(String.format("도메인 이슈 #%d에 관해서 이야기해보고 싶어요", 1))
			.build();
		issueCommentRepository.save(issueComment);
		IssueComment issueCommentDirector = IssueComment.builder()
			.user(user)
			.issue(issue)
			.contents(String.format("부장님이랑 도메인 이슈 #%d에 관해서 이야기해보고 싶어요", 1))
			.build();
		issueCommentRepository.save(issueCommentDirector);
		IssueComment issueCommentManager = IssueComment.builder()
			.user(user)
			.issue(issue)
			.contents(String.format("매니저님이랑 도메인 이슈 #%d에 관해서 이야기해보고 싶어요", 1))
			.build();
		issueCommentRepository.save(issueCommentManager);
	}

	@Test
	@DisplayName("공통 조건에 따른 조회쿼리 IssueComment 리스트를 구합니다.")
	public void 댓글_공통_조건조회() {
		// GIVEN
		IssueCommentSelectCondition commonCondition = IssueCommentSelectCondition.builder()
			.contents("이야기")
			.build();

		// WHEN
		List<IssueCommentReadResponseDto> responseDtos = issueCommentCustomRepository.selectByCondition(savedIssue.getId(),
			commonCondition);

		// THEN
		assertEquals(3, responseDtos.size());
	}

	@Test
	@DisplayName("유니크 조건에 따른 조회쿼리 IssueComment 리스트를 구합니다.")
	public void 댓글_유니크_조건조회() {
		// GIVEN
		IssueCommentSelectCondition condition = IssueCommentSelectCondition.builder()
			.contents("부장님")
			.build();

		// WHEN
		List<IssueCommentReadResponseDto> responseDtos = issueCommentCustomRepository.selectByCondition(savedIssue.getId(), condition);

		// THEN
		assertEquals(1, responseDtos.size());
	}

	@Test
	@DisplayName("공통 조건에 따라 조회 이후 기본 페이징 처리를 합니다.")
	public void 댓글_공통_조건조회_페이징_기본() {
		// GIVEN
		IssueCommentSelectCondition commonCondition = IssueCommentSelectCondition.builder()
			.contents("이야기")
			.build();

		// WHEN
		Pageable pageable = PageRequest.of(0, 3);
		Page<IssueCommentReadResponseDto> responseDtos = issueCommentCustomRepository.selectByConditionPaging(
			savedIssue.getId(), commonCondition, pageable);

		// THEN
		assertEquals(3, responseDtos.getSize());
	}

	@Test
	@DisplayName("공통 조건에 따라 조회, 정렬기준에 따른 정렬 이후 페이징 처리를 합니다.")
	public void 댓글_공통_조건조회_페이징_정렬() {
		// GIVEN
		User user = userRepository.save(User.builder().build());
		IssueComment issueComment = IssueComment.builder()
			.user(user)
			.issue(savedIssue)
			.contents(String.format("저도요저도!! 저도 부장님이랑 도메인 이슈 #%d에 관해서 이야기해보고 싶어요", 1))
			.build();
		issueCommentRepository.save(issueComment);
		IssueCommentSelectCondition commonCondition = IssueCommentSelectCondition.builder()
			.contents("이야기")
			.build();

		// WHEN
		Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
		Page<IssueCommentReadResponseDto> responseDtos = issueCommentCustomRepository.selectByConditionPaging(
			savedIssue.getId(), commonCondition, pageable);
		List<IssueCommentReadResponseDto> commentReadResponseDtos = responseDtos.stream().toList();

		// THEN
		// get recently saved comment, which is the first element of "comment id" descending order of comments
		int index = IntStream.range(0, commentReadResponseDtos.size())
			.filter(i -> issueComment.getContents()
				.equals(commentReadResponseDtos.get(i).getContents())
			)
			.findFirst().getAsInt();
		assertEquals(0, index);
	}
}