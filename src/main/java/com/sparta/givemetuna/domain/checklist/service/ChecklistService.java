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
import com.sparta.givemetuna.domain.checklist.repository.ChecklistRepository;
import com.sparta.givemetuna.domain.checklist.temp.card.service.CardTempService;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.validation.Valid;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChecklistService {

	private final ChecklistRepository checklistRepository;
	private final CardTempService cardTempService;

	public ChecklistCreateResponseDto createChecklist(ChecklistCreateRequestDto checklistCreateRequestDto, Long boardId, Long stageId,
		Long cardId, User user) {
		// 보드아이디와 사용자 유저정보를 가지고 userRole 알아내기
		// 관리자, 매니저, 카드를 부여받은 유저까지 생성가능하다.
		// 단, 일반유저는 부여받은 해당 카드에만 체크리스트를 생성할 수 있다.

		// 카드 레포에서 해당 카드 정보 가져오기
		Card card = cardTempService.getCard(cardId);

		Checklist checklist = Checklist.of(checklistCreateRequestDto, false, 4, card, user); // 카드정보, 유저정보 추가
		Checklist savedChecklist = checklistRepository.save(checklist);
		return ChecklistCreateResponseDto.of(savedChecklist);
	}

	public ChecklistContentsUpdateResponseDto updateChecklistConetents(
		@Valid ChecklistContentsUpdateRequestDto checklistContentsUpdateRequestDto,
		Long boardId,
		Long stageId,
		Long cardId,
		Long checklistId, User user) {
		Checklist checklist = checklistRepository.findById(checklistId).orElseThrow(() -> new IllegalArgumentException("해당 체크리스트는 업습니다."));
		// 체크리스트 작성자 or 매니저 or 관리자 까지 수정가능하다.
		// boardId와 user 정보로 해당 보드에서 이 유저가 어떤 권한인지 확인한다.
		// 관리자 가능
		// 해당 카드를 만든 매니저 가능
		// 체크리스트 생성한 유저 가능
		if (!Objects.equals(checklist.getUser().getId(), user.getId())) {
			throw new IllegalArgumentException("체크리스트 생성자만 수정이 가능합니다.");
		}

		checklist.setContents(checklistContentsUpdateRequestDto.getContents());
		return ChecklistContentsUpdateResponseDto.of(checklist);
	}

	public ChecklistCheckUpdateResponseDto updateChecklistchecks(Long boardId, Long stageId, Long cardId, Long checklistId, User user) {
		Checklist checklist = checklistRepository.findById(checklistId).orElseThrow(() -> new IllegalArgumentException("해당 체크리스트는 업습니다."));
		// 체크리스트 작성자 or 매니저 or 관리자 까지 수정가능하다.
		// boardId와 user 정보로 해당 보드에서 이 유저가 어떤 권한인지 확인한다.
		// 관리자 가능
		// 해당 카드를 만든 매니저 가능
		// 체크리스트 생성한 유저 가능

		checklist.setCheck(!checklist.getCheck());
		return ChecklistCheckUpdateResponseDto.of(checklist);
	}

	public ChecklistPriorityUpdateResponseDto updateChecklistPriorities(ChecklistPriorityUpdateRequestDto checklistPriorityUpdateRequestDto,
		Long boardId,
		Long stageId, Long cardId, Long checklistId, User user) {
		Checklist checklist = checklistRepository.findById(checklistId).orElseThrow(() -> new IllegalArgumentException("해당 체크리스트는 업습니다."));
		// 체크리스트 작성자 or 매니저 or 관리자 까지 수정가능하다.
		// boardId와 user 정보로 해당 보드에서 이 유저가 어떤 권한인지 확인한다.
		// 관리자 가능
		// 해당 카드를 만든 매니저 가능
		// 체크리스트 생성한 유저 가능

		checklist.setPriority(checklistPriorityUpdateRequestDto.getPriority());
		return ChecklistPriorityUpdateResponseDto.of(checklist);
	}

	public ChecklistDeleteResponseDto deleteChecklist(Long boardId, Long stageId, Long cardId, Long checklistId, User user) {
		Checklist checklist = checklistRepository.findById(checklistId).orElseThrow(() -> new IllegalArgumentException("해당 체크리스트는 업습니다."));
		// 체크리스트 작성자 or 매니저 or 관리자 까지 삭제가능하다.
		// boardId와 user 정보로 해당 보드에서 이 유저가 어떤 권한인지 확인한다.
		// 관리자 가능
		// 해당 카드를 만든 매니저 가능
		// 체크리스트 생성한 유저 가능
		Long id = checklist.getId();
		checklistRepository.delete(checklist);
		return ChecklistDeleteResponseDto.of(id);
	}
}
