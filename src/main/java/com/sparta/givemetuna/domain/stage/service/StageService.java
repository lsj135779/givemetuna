package com.sparta.givemetuna.domain.stage.service;

import com.sparta.givemetuna.domain.stage.dto.CreateStageRequestDto;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.stage.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StageService {
    private final StageRepository stageRepository;

    public Stage createStage(CreateStageRequestDto requestDto) {
        Stage stage = new Stage(requestDto);
        stageRepository.save(stage);
        return stage;
    }
}
