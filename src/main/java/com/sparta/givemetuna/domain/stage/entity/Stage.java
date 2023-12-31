package com.sparta.givemetuna.domain.stage.entity;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.stage.dto.CreateStageRequestDto;
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
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
@Getter
@NoArgsConstructor
@Table(name = "stage")
public class Stage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "stage", targetEntity = Card.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Card> userCards = new ArrayList<>();

	@OneToMany(mappedBy = "stage", targetEntity = Checklist.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Checklist> checklists = new ArrayList<>();

	public Stage(CreateStageRequestDto requestDto) { this.category = requestDto.getCategory(); }
}
