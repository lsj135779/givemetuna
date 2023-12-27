package com.sparta.givemetuna.domain.column.service;

import com.sparta.givemetuna.domain.column.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StageService {
    private final StageRepository stageRepository;
}
