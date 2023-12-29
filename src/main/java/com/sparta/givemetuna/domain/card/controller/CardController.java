package com.sparta.givemetuna.domain.card.controller;

import com.sparta.givemetuna.domain.card.dto.request.CreateCardRequestDto;
import com.sparta.givemetuna.domain.card.dto.response.CreateCardResponseDto;
import com.sparta.givemetuna.domain.core.service.CardMatcherService;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{board_id}/stage/{stage_id}/cards")
public class CardController {

    private final CardMatcherService cardMatcherService;

    @PostMapping
    public CreateCardResponseDto createCard(@PathVariable Long board_id,
            @PathVariable Long stage_id,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CreateCardRequestDto requestDto) {
        CreateCardResponseDto responseDto = cardMatcherService.createCard(board_id, stage_id,
                userDetails.getUser(), requestDto);
        return responseDto;
    }
}
