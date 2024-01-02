package com.sparta.givemetuna.domain.core.service;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChecklistMatcherService {

	private final CardRepository cardRepository;

	public Card getCard(Long cardId) {

		return cardRepository.findById(cardId)
			.orElseThrow(() -> new NullPointerException("없는 카드입니다."));
	}
}
