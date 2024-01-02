package com.sparta.givemetuna.domain.checklist.temp.cardTemp;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardTempService {

	private final CardRepository cardRepository;

	public Card getCard(Long cardId) {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new IllegalArgumentException("해당하는 카드정보가 없습니다."));
		return card;
	}
}