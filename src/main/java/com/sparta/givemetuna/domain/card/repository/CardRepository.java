package com.sparta.givemetuna.domain.card.repository;

import com.sparta.givemetuna.domain.card.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    Page<Card> findAllByStageId(Long stage_id, Pageable pageable);
}