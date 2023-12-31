package com.sparta.givemetuna.domain.card.repository;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByStageId(Stage stage);
}