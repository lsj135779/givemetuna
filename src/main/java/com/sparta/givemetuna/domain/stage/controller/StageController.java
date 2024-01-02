package com.sparta.givemetuna.domain.stage.controller;

import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.stage.dto.CreateStageRequestDto;
import com.sparta.givemetuna.domain.stage.dto.CreateStageResponseDto;
import com.sparta.givemetuna.domain.stage.dto.DeleteStageResponseDto;
import com.sparta.givemetuna.domain.stage.dto.SingleStageResponseDto;
import com.sparta.givemetuna.domain.stage.dto.UpdateStageRequestDto;
import com.sparta.givemetuna.domain.stage.dto.UpdateStageResponseDto;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.stage.service.StageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stages")
@SecurityRequirement(name = "Bearer Authentication")
public class StageController {

	private final StageService stageService;

	@PostMapping("/{board_id}/stages")
	public ResponseEntity<CreateStageResponseDto> createStage(@PathVariable Long board_id,
		@RequestBody CreateStageRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {
		CreateStageResponseDto response = stageService.createStage(board_id, requestDto, userDetails.getUser());
		return ResponseEntity.ok().body(response);
	}

	//stage 수정
	@PatchMapping("/{stage_id}")
	public ResponseEntity<UpdateStageResponseDto> updateStage(
		@PathVariable("stage_id") Long stageId,
		@RequestBody UpdateStageRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		UpdateStageResponseDto response = stageService.updateStage(stageId, requestDto, userDetails.getUser());
		return ResponseEntity.ok().body(response);
	}

	// 페이징 및 정렬
	@GetMapping
	public void getStages() {
	}

	//stage 삭제
	@DeleteMapping("/{stage_id}")
	public ResponseEntity<DeleteStageResponseDto> deleteStage(@PathVariable Long stage_id,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {
		stageService.deleteStage(stage_id, userDetails.getUser());
		return ResponseEntity.ok().body(new DeleteStageResponseDto("Stage deleted successfully"));
	}

	// stage 단건 조회
	@GetMapping("/{stage_id}")
	public ResponseEntity<SingleStageResponseDto> getStage(@PathVariable(name = "stage_id") Long stageId) {
		Stage stage = stageService.getStageById(stageId);
		SingleStageResponseDto response = SingleStageResponseDto.of(stage);
		return ResponseEntity.ok().body(response);
	}
}
