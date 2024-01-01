package com.sparta.givemetuna.domain.stage.service;

import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.stage.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StageService {

    private final StageRepository stageRepository;


    public Stage checkStage(Long boardId, Long stageId) {

        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new NullPointerException("스테이지가 없습니다."));

        if (!stage.getBoard().getId().equals(boardId)) {
            throw new IllegalArgumentException("보드에 해당 스테이지가 없습니다.");
        }

        return stage;
    }
}
