package com.sparta.givemetuna.domain.column.controller;

import com.sparta.givemetuna.domain.column.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{board_id}/columns")
public class StageController {

    private final StageService stageService;

    @PostMapping
    private void createStage() {

    }

    @PatchMapping("/{column_id}")
    private void updateStage() {

    }

    // 페이징 및 정렬

    @DeleteMapping("/{column_id}")
    private void deleteStage() {

    }

    @GetMapping("/{column_id}")
    private void getStage() {

    }
}
