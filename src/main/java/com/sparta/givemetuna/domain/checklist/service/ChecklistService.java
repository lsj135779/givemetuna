package com.sparta.givemetuna.domain.checklist.service;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.exception.CardAssignorInvalidAuthorizationException;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistCheckUpdateResponseDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistContentsUpdateRequestDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistContentsUpdateResponseDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistCreateRequestDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistCreateResponseDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistDeleteResponseDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistPriorityUpdateRequestDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistPriorityUpdateResponseDto;
import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.checklist.entity.Priority;
import com.sparta.givemetuna.domain.checklist.exception.DeleteChecklistInvalidAuthorizationException;
import com.sparta.givemetuna.domain.checklist.exception.SelectChecklistNotFoundException;
import com.sparta.givemetuna.domain.checklist.exception.UpdateChecklistInvalidAuthorizationException;
import com.sparta.givemetuna.domain.checklist.repository.ChecklistRepository;
import com.sparta.givemetuna.domain.core.service.ChecklistMatcherService;
import com.sparta.givemetuna.domain.user.entity.Role;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.global.validator.BoardUserRoleValidator;
import jakarta.transaction.Transactional;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChecklistService {

	private final ChecklistRepository checklistRepository;

	private final ChecklistMatcherService checklistMatcherService;
	private final BoardUserRoleValidator boardUserRoleValidator;

	public ChecklistCreateResponseDto createChecklist(
		ChecklistCreateRequestDto checklistCreateRequestDto, Long boardId, Long stageId,
		Long cardId, User user) {
		// 보드아이디와 사용자 유저정보를 가지고 userRole 알아내기
		Role role = boardUserRoleValidator.getRole(boardId, user.getId());
		// 카드 레포에서 해당 카드 정보 가져오기
		Card card = checklistMatcherService.getCard(cardId);

		// 관리자, 카드생성한 매니저, 카드를 부여받은 유저까지 생성가능하다.
		// role이 manager인 경우
		if (role.equals(Role.TEAM_MANAGER)) {
			if (!Objects.equals(card.getCreator().getId(), user.getId())) {
				throw new CardAssignorInvalidAuthorizationException();
			}
		}

		// role이 user인 경우
		if (role.equals(Role.WORKER)) {
			checklistRepository.findFirstByAssigneeAndCardId(user.getId(), cardId)
				.orElseThrow(SelectChecklistNotFoundException::new);
		}

		Checklist newChecklist = Checklist.of(checklistCreateRequestDto, false, Priority.NON, true,
			card, user); // 카드정보, 유저정보 추가
		Checklist savedChecklist = checklistRepository.save(newChecklist);
		return ChecklistCreateResponseDto.of(savedChecklist);
	}

	@Transactional
	public ChecklistContentsUpdateResponseDto updateChecklistConetents(
		ChecklistContentsUpdateRequestDto checklistContentsUpdateRequestDto, Long boardId,
		Long stageId, Long cardId, Long checklistId, User user)
		throws IllegalArgumentException {
		// 보드아이디와 사용자 유저정보를 가지고 userRole 알아내기
		Role role = boardUserRoleValidator.getRole(boardId, user.getId());
		// 카드 레포에서 해당 카드 정보 가져오기
		Card card = checklistMatcherService.getCard(cardId);
		Checklist checklist = getChecklist(checklistId);
		// 수정 가능 여부 판단
		updatableChecklist(role, card, checklist, user);

		checklist.setContents(checklistContentsUpdateRequestDto.getContents());
		return ChecklistContentsUpdateResponseDto.of(checklist);
	}

	@Transactional
	public ChecklistCheckUpdateResponseDto updateChecklistchecks(Long boardId, Long stageId,
		Long cardId, Long checklistId, User user) throws IllegalArgumentException {
		// 보드아이디와 사용자 유저정보를 가지고 userRole 알아내기
		Role role = boardUserRoleValidator.getRole(boardId, user.getId());
		// 카드 레포에서 해당 카드 정보 가져오기
		Card card = checklistMatcherService.getCard(cardId);
		Checklist checklist = getChecklist(checklistId);
		// 수정 가능 여부 판단
		updatableChecklist(role, card, checklist, user);

		checklist.setCheck(!checklist.getCheck());
		return ChecklistCheckUpdateResponseDto.of(checklist);
	}

	@Transactional
	public ChecklistPriorityUpdateResponseDto updateChecklistPriorities(
		ChecklistPriorityUpdateRequestDto checklistPriorityUpdateRequestDto, Long boardId,
		Long stageId, Long cardId, Long checklistId, User user)
		throws IllegalArgumentException {
		// 보드아이디와 사용자 유저정보를 가지고 userRole 알아내기
		Role role = boardUserRoleValidator.getRole(boardId, user.getId());
		// 카드 레포에서 해당 카드 정보 가져오기
		Card card = checklistMatcherService.getCard(cardId);
		Checklist checklist = getChecklist(checklistId);
		// 수정 가능 여부 판단
		updatableChecklist(role, card, checklist, user);

		checklist.setPriority(checklistPriorityUpdateRequestDto.getPriority());
		return ChecklistPriorityUpdateResponseDto.of(checklist);
	}

	@Transactional
	public ChecklistDeleteResponseDto deleteChecklist(Long boardId, Long stageId, Long cardId,
		Long checklistId, User user) throws IllegalArgumentException {
		// 보드아이디와 사용자 유저정보를 가지고 userRole 알아내기
		Role role = boardUserRoleValidator.getRole(boardId, user.getId());
		// 카드 레포에서 해당 카드 정보 가져오기
		Card card = checklistMatcherService.getCard(cardId);
		Checklist checklist = getChecklist(checklistId);
		// 관리자 가능
		// 해당 카드를 만든 매니저 가능
		if (role.equals(Role.TEAM_MANAGER)) {
			if (!Objects.equals(card.getCreator().getId(), user.getId())) {
				throw new DeleteChecklistInvalidAuthorizationException();
			}
		}

		// 체크리스트 생성한 유저 가능
		if (role.equals(Role.WORKER)) {
			if (!Objects.equals(checklist.getUser().getId(), user.getId())) {
				throw new DeleteChecklistInvalidAuthorizationException();
			}
		}

		// 첫 번째 체크리스트는 삭제 불가능
		if (!checklist.getDeletable()) {
			throw new DeleteChecklistInvalidAuthorizationException();
		}

		Long id = checklist.getId();
		checklistRepository.delete(checklist);
		return ChecklistDeleteResponseDto.of(id);
	}

	private Checklist getChecklist(Long checklistId) {
		return checklistRepository.findById(checklistId)
			.orElseThrow(SelectChecklistNotFoundException::new);
	}

	private void updatableChecklist(Role role, Card card, Checklist checklist, User user)
		throws IllegalArgumentException {
		// 관리자 가능
		// 해당 카드를 만든 매니저 가능
		if (role.equals(Role.TEAM_MANAGER)) {
			if (!Objects.equals(card.getCreator().getId(), user.getId())) {
				throw new UpdateChecklistInvalidAuthorizationException();
			}
		}

		// 체크리스트 생성한 유저 가능
		if (role.equals(Role.WORKER)) {
			if (!Objects.equals(checklist.getUser().getId(), user.getId())) {
				throw new UpdateChecklistInvalidAuthorizationException();
			}
		}
	}

	// 처음 빈카드에서 카드를 부여할 때 체크리스트 만드는 메서드 추가
	public void firstCreateChecklist(Card card, User user) { // Parameter로 부여받은 유저정보, 카드정보를 가져온다.
		// builder로 Checklist객체 만들어서 저장하기
		Checklist checklist = Checklist.builder().contents("받은 카드를 확인했습니까?").check(false)
			.priority(Priority.NON).deletable(false).card(card).user(user).build();
		checklistRepository.save(checklist);
	}
}
