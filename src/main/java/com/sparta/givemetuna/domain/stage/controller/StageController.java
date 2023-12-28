package com.sparta.givemetuna.domain.stage.controller;

import com.sparta.givemetuna.domain.stage.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stages")
public class StageController {

	private final StageService stageService;

	@PostMapping("/{board_id}/stages")
	public void createStage() {

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
