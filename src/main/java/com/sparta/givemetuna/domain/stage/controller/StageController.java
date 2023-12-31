package com.sparta.givemetuna.domain.stage.controller;

import com.sparta.givemetuna.domain.stage.dto.CreateStageRequestDto;
import com.sparta.givemetuna.domain.stage.dto.CreateStageResponseDto;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.stage.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stages")
public class StageController {

	private final StageService stageService;

	@PostMapping("/{board_id}/stages")
	public ResponseEntity<CreateStageResponseDto> createStage(@RequestBody CreateStageRequestDto requestDto) {
		Stage stage = stageService.createStage(requestDto);
		CreateStageResponseDto response = new CreateStageResponseDto(stage);
		return ResponseEntity.ok().body(response);
	}

	@PatchMapping("/{stage_id}")
	public void updateStage() {

	}

	// 페이징 및 정렬

	@DeleteMapping("/{stage_id}")
	public void deleteStage() {

	}

	@GetMapping("/{stage_id}")
	public void getStage() {

	}

	@GetMapping
	public void getStages() {

	}
}
