package com.sparta.givemetuna.domain.stage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.stage.dto.UpdateStageRequestDto;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

	@JsonIgnore
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
