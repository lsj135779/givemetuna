package com.sparta.givemetuna.domain.stage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.stage.dto.UpdateStageRequestDto;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "stage")
public class Stage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String category;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "stage", targetEntity = Card.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Card> userCards = new ArrayList<>();

	public Stage(Board board, String category, User user) {
		this.board = board;
		this.category = category;
		this.user = user;
	}

	public void setCategory(UpdateStageRequestDto requestDto) {
		this.category = requestDto.getCategory();
	}
}
