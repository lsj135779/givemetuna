package com.sparta.givemetuna.domain.board.service;

import com.sparta.givemetuna.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

}
