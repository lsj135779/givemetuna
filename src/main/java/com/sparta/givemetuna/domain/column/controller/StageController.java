package com.sparta.givemetuna.domain.column.controller;

import com.sparta.givemetuna.domain.column.dto.CreateStageRequestDto;
import com.sparta.givemetuna.domain.column.dto.CreateStageResponseDto;
import com.sparta.givemetuna.domain.column.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class StageController {

    private final StageService stageService;

    @PostMapping("/{board_id}/columns")
    public void createStage() {

    }

    @PatchMapping("/{column_id}")
    public void updateStage() {

    }

    // 페이징 및 정렬

    @DeleteMapping("/{column_id}")
    public void deleteStage() {

    }

    @GetMapping("/{column_id}")
    public void getStage() {

    }

    @GetMapping
    public void getStages() {

    }
}
