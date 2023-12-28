package com.sparta.givemetuna.domain.stage.service;

import com.sparta.givemetuna.domain.stage.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StageService {
    private final StageRepository stageRepository;
}
