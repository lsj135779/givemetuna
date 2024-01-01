package com.sparta.givemetuna.domain.checklist.service;

import com.sparta.givemetuna.domain.card.entity.Card;
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
import com.sparta.givemetuna.domain.checklist.repository.ChecklistRepository;
import com.sparta.givemetuna.domain.checklist.temp.boardTemp.BoardTempService;
import com.sparta.givemetuna.domain.checklist.temp.cardTemp.CardTempService;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.transaction.Transactional;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChecklistService {

	private final ChecklistRepository checklistRepository;

	private final CardTempService cardTempService;

	private final BoardTempService boardTempService;

	public ChecklistCreateResponseDto createChecklist(ChecklistCreateRequestDto checklistCreateRequestDto, Long boardId, Long stageId,
		Long cardId, User user) throws IllegalArgumentException {
		// 보드아이디와 사용자 유저정보를 가지고 userRole 알아내기
		String role = boardTempService.getRole(boardId, user.getId());
		// 카드 레포에서 해당 카드 정보 가져오기
		Card card = cardTempService.getCard(cardId);
		// 체크리스트 레포에서 사용자 유저 정보로 체크리스트 정보 가져오기
		Checklist checklist = checklistRepository.findFirstByAssignee(user.getId())
			.orElseThrow(() -> new IllegalArgumentException("해당 유저는 부여받은 정보가 없습니다."));

		// 관리자, 카드생성한 매니저, 카드를 부여받은 유저까지 생성가능하다.
		// role이 manager인 경우
//		if (role.equals("manager")) {
//			if (!Objects.equals(card.getCreator(), user.getId())) {
//				throw new IllegalArgumentException("카드 생성자만 체크리스트 생성이 가능합니다.");
//			}
//		}

		// role이 user인 경우
		if (role.equals("user")) {
			if (!Objects.equals(card.getId(), checklist.getCard().getId())) {
				throw new IllegalArgumentException("카드를 부여받은 유저만 체크리스트 생성이 가능합니다.");
			}
		}

		Checklist newChecklist = Checklist.of(checklistCreateRequestDto, false, Priority.없음, true, card, user); // 카드정보, 유저정보 추가
		Checklist savedChecklist = checklistRepository.save(newChecklist);
		return ChecklistCreateResponseDto.of(savedChecklist);
	}

	@Transactional
	public ChecklistContentsUpdateResponseDto updateChecklistConetents(
		ChecklistContentsUpdateRequestDto checklistContentsUpdateRequestDto,
		Long boardId,
		Long stageId,
		Long cardId,
		Long checklistId, User user) throws IllegalArgumentException {
		// 보드아이디와 사용자 유저정보를 가지고 userRole 알아내기
		String role = boardTempService.getRole(boardId, user.getId());
		// 카드 레포에서 해당 카드 정보 가져오기
		Card card = cardTempService.getCard(cardId);
		Checklist checklist = checklistRepository.findById(checklistId)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 체크리스트가 없습니다."));
		// 수정 가능 여부 판단
		updatableChecklist(role, card, checklist, user);

		checklist.setContents(checklistContentsUpdateRequestDto.getContents());
		return ChecklistContentsUpdateResponseDto.of(checklist);
	}

	@Transactional
	public ChecklistCheckUpdateResponseDto updateChecklistchecks(Long boardId, Long stageId, Long cardId, Long checklistId, User user)
		throws IllegalArgumentException {
		// 보드아이디와 사용자 유저정보를 가지고 userRole 알아내기
		String role = boardTempService.getRole(boardId, user.getId());
		// 카드 레포에서 해당 카드 정보 가져오기
		Card card = cardTempService.getCard(cardId);
		Checklist checklist = checklistRepository.findById(checklistId).orElseThrow(() -> new IllegalArgumentException("해당 체크리스트는 업습니다."));
		// 수정 가능 여부 판단
		updatableChecklist(role, card, checklist, user);

		checklist.setCheck(!checklist.getCheck());
		return ChecklistCheckUpdateResponseDto.of(checklist);
	}

	@Transactional
	public ChecklistPriorityUpdateResponseDto updateChecklistPriorities(ChecklistPriorityUpdateRequestDto checklistPriorityUpdateRequestDto,
		Long boardId,
		Long stageId, Long cardId, Long checklistId, User user) throws IllegalArgumentException {
		// 보드아이디와 사용자 유저정보를 가지고 userRole 알아내기
		String role = boardTempService.getRole(boardId, user.getId());
		// 카드 레포에서 해당 카드 정보 가져오기
		Card card = cardTempService.getCard(cardId);
		Checklist checklist = checklistRepository.findById(checklistId).orElseThrow(() -> new IllegalArgumentException("해당 체크리스트는 업습니다."));
		// 수정 가능 여부 판단
		updatableChecklist(role, card, checklist, user);

		checklist.setPriority(checklistPriorityUpdateRequestDto.getPriority());
		return ChecklistPriorityUpdateResponseDto.of(checklist);
	}

	@Transactional
	public ChecklistDeleteResponseDto deleteChecklist(Long boardId, Long stageId, Long cardId, Long checklistId, User user)
		throws IllegalArgumentException {
		// 보드아이디와 사용자 유저정보를 가지고 userRole 알아내기
		String role = boardTempService.getRole(boardId, user.getId());
		// 카드 레포에서 해당 카드 정보 가져오기
		Card card = cardTempService.getCard(cardId);
		Checklist checklist = checklistRepository.findById(checklistId).orElseThrow(() -> new IllegalArgumentException("해당 체크리스트는 업습니다."));
		// 체크리스트 작성자 or 매니저 or 관리자 까지 삭제가능하다.
		// boardId와 user 정보로 해당 보드에서 이 유저가 어떤 권한인지 확인한다.
		// 관리자 가능
		// 해당 카드를 만든 매니저 가능
//		if (role.equals("manager")) {
//			if (!Objects.equals(card.getCreator(), user.getId())) {
//				// 카드를 생성한 매니저만 삭제할 수 있습니다.
//			}
//		}

		// 체크리스트 생성한 유저 가능
		if (role.equals("user")) {
			if (!Objects.equals(checklist.getAssignee().getId(), user.getId())) {
				throw new IllegalArgumentException("체크리스트 생성자만 삭제가 가능합니다.");
			}
		}

		// 첫 번째 체크리스트는 삭제 불가능
		if (!checklist.getDeletable()) {
			throw new IllegalArgumentException("삭제할 수 없는 체크리스트입니다.");
		}

		Long id = checklist.getId();
		checklistRepository.delete(checklist);
		return ChecklistDeleteResponseDto.of(id);
	}

	private void updatableChecklist(String role, Card card, Checklist checklist, User user) throws IllegalArgumentException {
		// 관리자 가능
		// 해당 카드를 만든 매니저 가능
//		if (role.equals("manager")) {
//			if (!Objects.equals(card.getCreator(), user.getId())) {
//				throw new IllegalArgumentException("카드 생성자만 수정이 가능합니다.");
//			}
//		}

		// 체크리스트 생성한 유저 가능
		if (role.equals("user")) {
			if (!Objects.equals(checklist.getAssignee().getId(), user.getId())) {
				throw new IllegalArgumentException("체크리스트 생성자만 수정이 가능합니다.");
			}
		}
	}

	// 처음 빈카드에서 카드를 부여할 때 체크리스트 만드는 메서드 추가
	public void firstCreateChecklist(Card card, User user) { // Parameter로 부여받은 유저정보, 카드정보를 가져온다.
		// builder로 Checklist객체 만들어서 저장하기
		Checklist checklist = Checklist.builder()
			.contents("받은 카드를 확인했습니까?")
			.check(false)
			.priority(Priority.없음)
			.deletable(false)
			.card(card)
			.assignee(user).build();
		checklistRepository.save(checklist);
	}
}
