package com.sparta.givemetuna.domain.stage.controller;

import com.sparta.givemetuna.domain.stage.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
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
