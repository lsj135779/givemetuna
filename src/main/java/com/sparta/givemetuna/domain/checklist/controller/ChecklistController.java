package com.sparta.givemetuna.domain.checklist.controller;

import com.sparta.givemetuna.domain.checklist.dto.ChecklistCheckUpdateResponseDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistContentsUpdateRequestDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistContentsUpdateResponseDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistCreateRequestDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistCreateResponseDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistPriorityUpdateRequestDto;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistPriorityUpdateResponseDto;
import com.sparta.givemetuna.domain.checklist.service.ChecklistService;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boards/{board_id}/stages/{stage_id}/cards/{card_id}/checklists")
@RequiredArgsConstructor
public class ChecklistController {

	private final ChecklistService checklistService;

	// 예외처리 로직 작성
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<CommonResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		FieldError fieldError = result.getFieldError();
		String errorMessage = fieldError.getDefaultMessage();

		CommonResponseDto responseDto = new CommonResponseDto(errorMessage, HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.badRequest().body(responseDto);
	}

	@PostMapping
	public ResponseEntity<ChecklistCreateResponseDto> createChecklist(
		@RequestBody @Valid ChecklistCreateRequestDto checklistCreateRequestDto,
		@PathVariable Long board_id, @PathVariable Long stage_id,
		@PathVariable Long card_id) { // 사용자 인증 정보 추가 @AuthenticationPrincipal UserDetailsImpl userDetails
		User user = User.builder().build();
		ChecklistCreateResponseDto checklistCreateResponseDto = checklistService.createChecklist(checklistCreateRequestDto, board_id,
			stage_id,
			card_id, user);
		return ResponseEntity.ok().body(checklistCreateResponseDto);
	}

	@PatchMapping("/{checklist_id}/contents")
	public ResponseEntity<ChecklistContentsUpdateResponseDto> updateChecklistContents(
		@RequestBody @Valid ChecklistContentsUpdateRequestDto checklistContentsUpdateRequestDto,
		@PathVariable Long board_id,
		@PathVariable Long stage_id, @PathVariable Long card_id,
		@PathVariable Long checklist_id) { // 사용자 인증 정보 추가 @AuthenticationPrincipal UserDetailsImpl userDetails
		User user = new User();
		ChecklistContentsUpdateResponseDto checklistContentsUpdateResponseDto = checklistService.updateChecklistConetents(
			checklistContentsUpdateRequestDto, board_id, stage_id, card_id, checklist_id, user);
		return ResponseEntity.ok().body(checklistContentsUpdateResponseDto);
	}

	@PatchMapping("/{checklist_id}/checks")
	public ResponseEntity<ChecklistCheckUpdateResponseDto> updateChecklistChecks(@PathVariable Long board_id, @PathVariable Long stage_id,
		@PathVariable Long card_id,
		@PathVariable Long checklist_id) { // 사용자 인증 정보 추가 @AuthenticationPrincipal UserDetailsImpl userDetails
		User user = new User();
		ChecklistCheckUpdateResponseDto checklistCheckUpdateResponseDto = checklistService.updateChecklistchecks(board_id, stage_id,
			card_id, checklist_id, user);
		return ResponseEntity.ok().body(checklistCheckUpdateResponseDto);
	}

	@PatchMapping("/{checklist_id}/priorities")
	public ResponseEntity<ChecklistPriorityUpdateResponseDto> updateChecklistPriorities(
		@RequestBody ChecklistPriorityUpdateRequestDto checklistPriorityUpdateRequestDto,
		@PathVariable Long board_id,
		@PathVariable Long stage_id, @PathVariable Long card_id,
		@PathVariable Long checklist_id) { // 사용자 인증 정보 추가 @AuthenticationPrincipal UserDetailsImpl userDetails
		User user = new User();
		ChecklistPriorityUpdateResponseDto checklistPriorityUpdateResponseDto = checklistService.updateChecklistPriorities(
			checklistPriorityUpdateRequestDto, board_id, stage_id, card_id, checklist_id, user);
		return ResponseEntity.ok().body(checklistPriorityUpdateResponseDto);
	}

	@DeleteMapping("/{checklist_id}")
	public void deleteChecklist(@PathVariable Long board_id, @PathVariable Long stage_id, @PathVariable Long card_id,
		@PathVariable Long checklist_id) { // 사용자 인증 정보 추가 @AuthenticationPrincipal UserDetailsImpl userDetails
		User user = new User();
		checklistService.deleteChecklist(board_id, stage_id, card_id, checklist_id, user);
	}
}
